package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.mapper.TicketDtoMapper;
import by.grsu.liceum.dto.ticket.SetTicketDto;
import by.grsu.liceum.dto.ticket.TicketFullDto;
import by.grsu.liceum.dto.ticket.TicketReadCodeDto;
import by.grsu.liceum.dto.ticket.TicketShortcutDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Bonus;
import by.grsu.liceum.entity.Ticket;
import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.exception.BonusWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidBonusCountException;
import by.grsu.liceum.exception.InvalidTicketCodeException;
import by.grsu.liceum.exception.TicketWithIdNotFoundException;
import by.grsu.liceum.repository.AccountRepository;
import by.grsu.liceum.repository.BonusRepository;
import by.grsu.liceum.repository.TicketRepository;
import by.grsu.liceum.service.TicketService;
import by.grsu.liceum.utils.Generator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final AccountRepository accountRepository;
    private final BonusRepository bonusRepository;

    @Override
    public List<TicketShortcutDto> findAll() {
        return ticketRepository.findAll().stream()
                .map(TicketDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    public TicketFullDto findById(long id) {
        Ticket ticket = Optional.ofNullable(ticketRepository.findById(id))
                .orElseThrow(() -> new TicketWithIdNotFoundException(id));

        return TicketDtoMapper.convertEntitToFullDto(ticket);
    }

    @Override
    @Transactional //todo если удалить бонус, в тикете его не будет, нужен какой-то метабонус который будет удаляться с завершением тайма этого бонуса или последнего экземпляра тикета
    public TicketFullDto setTicketToTheAccount(SetTicketDto ticketDto) {
        Account account = Optional.ofNullable(accountRepository.findById(ticketDto.getAccountId()))
                .orElseThrow(() -> new AccountWithIdNotFoundException(ticketDto.getAccountId()));

        Bonus bonus = Optional.ofNullable(bonusRepository.findById(ticketDto.getBonusId()))
                .orElseThrow(() -> new BonusWithIdNotFoundException(ticketDto.getBonusId()));

        if(bonus.getCount() > 1)
            bonus.setCount(bonus.getCount() - 1);
        else if (bonus.getCount() == 1)
            bonusRepository.deleteById(bonus.getId()); //todo test
        else
            throw new InvalidBonusCountException(bonus.getId(), bonus.getCount());

        Ticket ticket = Ticket.builder()
                .account(account)
                .bonus(bonus)
                .build();

        do{
            ticket.setCode(Generator.generateBonusToken());
        } while (ticketRepository.existsByCode(ticket.getCode()));

        ticketRepository.save(ticket);

        account.getTickets().add(ticket);

        return TicketDtoMapper.convertEntitToFullDto(ticket);
    }

    @Override
    @Transactional
    public void rollTicketBack(long id) {
        Ticket ticket = Optional.ofNullable(ticketRepository.findById(id))
                .orElseThrow(() -> new TicketWithIdNotFoundException(id));

        Bonus bonus = Optional.ofNullable(bonusRepository.findById(ticket.getBonus().getId()))
                .orElseThrow(() -> new BonusWithIdNotFoundException(ticket.getBonus().getId()));

        Account account = Optional.ofNullable(accountRepository.findById(ticket.getAccount().getId()))
                .orElseThrow(() -> new AccountWithIdNotFoundException(ticket.getAccount().getId()));

        account.getCard().setBalance(account.getCard().getBalance() + bonus.getPrice()); //todo mb not full price but with some percent
        bonus.setCount(bonus.getCount() + 1);
        
        //todo account tickets -> remove ticket
        ticketRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void readCode(TicketReadCodeDto readCodeDto){ //todo mb return response
        Ticket ticket = Optional.ofNullable(ticketRepository.findById(readCodeDto.getUuid()))
                .orElseThrow(() -> new TicketWithIdNotFoundException(readCodeDto.getUuid()));

        if(ticket.getCode().equals(readCodeDto.getCode())) //todo check user
            ticketRepository.deleteById(readCodeDto.getUuid());
        else
            throw new InvalidTicketCodeException(readCodeDto.getCode());
    }

    @Override
    public void deleteById(long id) {
        findById(id);

        ticketRepository.deleteById(id);
    }
}