package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.account.AccountCreationDto;
import by.grsu.liceum.dto.account.AccountCreationResponse;
import by.grsu.liceum.dto.bonus.BonusCreationDto;
import by.grsu.liceum.dto.mapper.AccountDtoMapper;
import by.grsu.liceum.dto.utils.GeneratorLoginDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Card;
import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.exception.NotEnoughBalanceError;
import by.grsu.liceum.repository.AccountRepository;
import by.grsu.liceum.repository.CardRepository;
import by.grsu.liceum.service.AdminService;
import by.grsu.liceum.utils.Generator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    @Override
    @Transactional
    public AccountCreationResponse createUserWithRole(AccountCreationDto creationDto) {
        Account account = AccountDtoMapper.convertDtoToEntity(creationDto);

        //todo change to creation dto in generator or add to mapper
        GeneratorLoginDto loginDto = GeneratorLoginDto.builder()
                .firstName(creationDto.getFirstName())
                .lastName(creationDto.getLastName())
                .fatherName(creationDto.getFatherName())
                .yearOfStartOfStudies(creationDto.getYearOfStartOfStudies())
                .build();

        account.setLogin(Generator.generateLogin(loginDto));
        account.setPassword(Generator.generatePassword());

        accountRepository.save(account);

        //todo migrate to card service
        Card card = Card.builder()
                .number(Generator.generateCardNumber())
                .balance(0)
                .account(account)
                .sentTransactions(new ArrayList<>())
                .takenTransactions(new ArrayList<>())
                .build();

        //todo test
        cardRepository.save(card);

        account.setCard(card);
        accountRepository.save(account);

        return AccountDtoMapper.convertEntityToCreationResponse(account);
    }

    @Override
    public void deleteUser(long id) {
        Optional.ofNullable(accountRepository.findById(id))
                .orElseThrow(() -> new AccountWithIdNotFoundException(id));

        accountRepository.deleteById(id);
    }

    @Override
    @Transactional//todo add transaction
    public void addRating(long accountId, int value) {
        Account account = Optional.ofNullable(accountRepository.findById(accountId))
                .orElseThrow(() -> new AccountWithIdNotFoundException(accountId));

        account.getCard().setBalance(account.getCard().getBalance() + value);

        accountRepository.save(account);
    }

    @Override
    @Transactional//todo add transaction
    public void getRating(long accountId, int value) {
        Account account = Optional.ofNullable(accountRepository.findById(accountId))
                .orElseThrow(() -> new AccountWithIdNotFoundException(accountId));

        if(account.getCard().getBalance() - value <= 0)
            throw new NotEnoughBalanceError(accountId, account.getCard().getBalance());

        account.getCard().setBalance(account.getCard().getBalance() - value);

        accountRepository.save(account);
    }

    @Override
    public void createBonus(BonusCreationDto creationDto) {

    }
}
