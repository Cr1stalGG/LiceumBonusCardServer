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
import by.grsu.liceum.entity.Ticket;
import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.exception.BonusWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidBonusCountException;
import by.grsu.liceum.exception.NotEnoughBalanceError;
import by.grsu.liceum.repository.AccountRepository;
import by.grsu.liceum.repository.BonusRepository;
import by.grsu.liceum.repository.TicketRepository;
import by.grsu.liceum.service.BonusService;
import by.grsu.liceum.utils.Generator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BonusServiceImpl implements BonusService {
    private final BonusRepository bonusRepository;
    private final AccountRepository accountRepository;
    private final TicketRepository ticketRepository;

    @Override
    public List<BonusShortcutDto> findAll() {
        return bonusRepository.findAll().stream()
                .map(BonusDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    public BonusFullDto findById(long id) {
        Bonus bonus = Optional.ofNullable(bonusRepository.findById(id))
                .orElseThrow(() -> new BonusWithIdNotFoundException(id));

        return BonusDtoMapper.convertEntityToFullDto(bonus);
    }

    @Override
    @Transactional//todo ADMIN_ROLE
    public BonusFullDto createBonus(BonusCreationDto creationDto) {
        Bonus bonus = BonusDtoMapper.convertDtoToEntity(creationDto);

        bonusRepository.save(bonus);

        return BonusDtoMapper.convertEntityToFullDto(bonus);
    }

    @Override
    @Transactional
    public TicketFullDto buyBonus(BonusBuyDto buyDto) {
        Account account = Optional.ofNullable(accountRepository.findById(buyDto.getAccountId()))
                .orElseThrow(() -> new AccountWithIdNotFoundException(buyDto.getAccountId()));

        Bonus bonus = Optional.ofNullable(bonusRepository.findById(buyDto.getBonusId()))
                .orElseThrow(() -> new BonusWithIdNotFoundException(buyDto.getBonusId()));

        if(account.getCard().getBalance() < bonus.getPrice())
            throw new NotEnoughBalanceError(account.getId(), bonus.getPrice());

        if(bonus.getCount() > 1)
            bonus.setCount(bonus.getCount() - 1);
        else if (bonus.getCount() == 1)
            bonusRepository.deleteById(bonus.getId()); //todo придумать норм решение чтоб тикеты при удалении бонуса не полетели нахуй
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

    @Override
    public void deleteById(long id) {
        findById(id);

        bonusRepository.deleteById(id);
    }
}
