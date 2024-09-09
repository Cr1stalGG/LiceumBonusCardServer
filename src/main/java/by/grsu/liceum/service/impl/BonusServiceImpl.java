package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.bonus.BonusBuyDto;
import by.grsu.liceum.dto.bonus.BonusCreationDto;
import by.grsu.liceum.dto.bonus.BonusFullDto;
import by.grsu.liceum.dto.bonus.BonusShortcutDto;
import by.grsu.liceum.dto.mapper.BonusDtoMapper;
import by.grsu.liceum.dto.mapper.TicketDtoMapper;
import by.grsu.liceum.dto.ticket.TicketFullDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Bonus;
import by.grsu.liceum.entity.Institution;
import by.grsu.liceum.entity.Response;
import by.grsu.liceum.entity.ResponseStatus;
import by.grsu.liceum.entity.Ticket;
import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.exception.BonusWithIdNotFoundException;
import by.grsu.liceum.exception.InstitutionWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidBonusCountException;
import by.grsu.liceum.exception.InvalidPermissionsException;
import by.grsu.liceum.exception.NotEnoughBalanceError;
import by.grsu.liceum.exception.ResponseStatusWithNameNotFoundException;
import by.grsu.liceum.repository.AccountRepository;
import by.grsu.liceum.repository.BonusRepository;
import by.grsu.liceum.repository.InstitutionRepository;
import by.grsu.liceum.repository.ResponseRepository;
import by.grsu.liceum.repository.ResponseStatusRepository;
import by.grsu.liceum.repository.TicketRepository;
import by.grsu.liceum.service.BonusService;
import by.grsu.liceum.service.enums.ResponseStatusConstant;
import by.grsu.liceum.service.enums.RoleConstant;
import by.grsu.liceum.utils.Generator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@PropertySource("classpath:business_settings.properties")
@RequiredArgsConstructor
public class BonusServiceImpl implements BonusService {
    private final BonusRepository bonusRepository;
    private final AccountRepository accountRepository;
    private final TicketRepository ticketRepository;
    private final InstitutionRepository institutionRepository;
    private final ResponseStatusRepository responseStatusRepository;
    private final ResponseRepository responseRepository;

    @Override
    public List<BonusShortcutDto> findAllByInstitutionId(UUID institutionId) {
        return bonusRepository.findAllByInstitution_Id(institutionId).stream()
                .filter(x -> x.getCount() > 0)
                .map(BonusDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    public BonusFullDto findById(UUID institutionId, UUID id) {
        Bonus bonus = Optional.ofNullable(bonusRepository.findById(id))
                .orElseThrow(() -> new BonusWithIdNotFoundException(id));

        if(!bonus.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        return BonusDtoMapper.convertEntityToFullDto(bonus);
    }

    @Override
    @Transactional
    public BonusFullDto createBonus(UUID institutionId, BonusCreationDto creationDto) {
        Institution institution = Optional.ofNullable(institutionRepository.findById(institutionId))
                .orElseThrow(() -> new InstitutionWithIdNotFoundException(institutionId));

        Bonus bonus = BonusDtoMapper.convertDtoToEntity(creationDto);
        bonus.setInstitution(institution);

        bonusRepository.save(bonus);

        institution.getBonuses().add(bonus);

        return BonusDtoMapper.convertEntityToFullDto(bonus);
    }

    @Override
    @Transactional
    public TicketFullDto buyBonus(UUID institutionId, BonusBuyDto buyDto) {
        Account account = Optional.ofNullable(accountRepository.findById(buyDto.getAccountId()))
                .orElseThrow(() -> new AccountWithIdNotFoundException(buyDto.getAccountId()));

        Bonus bonus = Optional.ofNullable(bonusRepository.findById(buyDto.getBonusId()))
                .orElseThrow(() -> new BonusWithIdNotFoundException(buyDto.getBonusId()));

        if(!account.getInstitution().getId().equals(institutionId) || !bonus.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        if(account.getCard().getBalance() < bonus.getPrice())
            throw new NotEnoughBalanceError(account.getId(), bonus.getPrice());

        if(bonus.getCount() > 0)
            bonus.setCount(bonus.getCount() - 1);
        else
            throw new InvalidBonusCountException(bonus.getId(), bonus.getCount());

        account.getCard().setBalance(account.getCard().getBalance() - bonus.getPrice());

        Ticket ticket = Ticket.builder()
                .account(account)
                .bonus(bonus)
                .build();

        do{
            ticket.setCode(Generator.generateBonusToken());
        } while (ticketRepository.existsByCode(ticket.getCode()));

        ticketRepository.save(ticket);

        return TicketDtoMapper.convertEntitToFullDto(ticket);
    }

    //@Scheduled(cron = "${scheduler.cron.interval.bonuses}") - real
    @Scheduled(fixedDelay = 120_000L) // test every minute
    @Transactional
    public void checkIfBonuseTimeEnded(){
        log.info("=======DELETE ALL USELESS BONUSES(time out off)=======");
        List<Bonus> bonuses = Optional.of(bonusRepository.findAll())
                .orElse(new ArrayList<>());

        for(Bonus bonus : bonuses){
            if(bonus.getTimeOfEnd() != null && bonus.getTimeOfEnd().compareTo(new Date(System.currentTimeMillis())) < 0){

                for(Ticket ticket : bonus.getTickets()){
                    log.info("Ticket with id {} removed", ticket.getId());

                    ResponseStatus responseStatus = Optional.ofNullable(responseStatusRepository.findByName(ResponseStatusConstant.RESPONSE_STATUS_TICKET_LIFE_ENDED.getValue()))
                            .orElseThrow(() -> new ResponseStatusWithNameNotFoundException(ResponseStatusConstant.RESPONSE_STATUS_TICKET_LIFE_ENDED.getValue()));

                    Account account = ticket.getAccount();
                    Response response = Response.builder()
                            .message("Ticket time is ended")
                            .responseStatus(responseStatus)
                            .timeOfResponse(new Date(System.currentTimeMillis()))
                            .account(account)
                            .build();

                    responseRepository.save(response);

                    responseStatus.getResponses().add(response);
                    account.getResponses().add(response);

                    ticketRepository.deleteById(ticket.getId());
                }

                Account admin = accountRepository.findByRoles_NameAndInstitution_Id(RoleConstant.ROLE_ADMIN.getValue(), bonus.getInstitution().getId()).iterator().next();

                ResponseStatus responseStatus = Optional.ofNullable(responseStatusRepository.findByName(ResponseStatusConstant.RESPONSE_STATUS_BONUS_LIFE_ENDED.getValue()))
                        .orElseThrow(() -> new ResponseStatusWithNameNotFoundException(ResponseStatusConstant.RESPONSE_STATUS_BONUS_LIFE_ENDED.getValue()));

                Response response = Response.builder()
                        .account(admin)
                        .responseStatus(responseStatus)
                        .message(String.format("Bonus '%s' deleted because of time ended", bonus.getName()))
                        .timeOfResponse(new Date(System.currentTimeMillis()))
                        .build();

                responseRepository.save(response);

                responseStatus.getResponses().add(response);
                admin.getResponses().add(response);

                log.info("Bonus with id {} removed", bonus.getId());
                bonusRepository.deleteById(bonus.getId());
            }
        }
        log.info("======================================================");
    }


    @Override
    public void deleteById(UUID institutionId, UUID id) {
        Bonus bonus = Optional.ofNullable(bonusRepository.findById(id))
                .orElseThrow(() -> new BonusWithIdNotFoundException(id));

        if(!bonus.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        bonusRepository.deleteById(id);
    }
}
