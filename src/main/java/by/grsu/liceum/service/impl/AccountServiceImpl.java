package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.account.AccountCreationDto;
import by.grsu.liceum.dto.account.AccountCreationResponse;
import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.mapper.AccountDtoMapper;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Card;
import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.repository.AccountRepository;
import by.grsu.liceum.service.AccountService;
import by.grsu.liceum.service.CardService;
import by.grsu.liceum.utils.Generator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CardService cardService;

    @Override
    public AccountFullDto findById(long id) {
        Account account = Optional.ofNullable(accountRepository.findById(id))
                .orElseThrow(() -> new AccountWithIdNotFoundException(id));

        return AccountDtoMapper.convertEntityToFullDto(account);
    }

    @Override
    public List<AccountShortcutDto> findAll() {
        return accountRepository.findAll().stream()
                .map(AccountDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    @Transactional //TODO ADMIN_ROLE
    public AccountCreationResponse createUserWithRole(AccountCreationDto creationDto) {
        Account account = AccountDtoMapper.convertDtoToEntity(creationDto);

        account.setLogin(Generator.generateLogin(AccountDtoMapper.convertCreationDtoToGeneratorDto(creationDto)));
        account.setPassword(Generator.generatePassword());

        accountRepository.save(account);

        //todo migrate to card service
        Card card = cardService.generateCard();

        //todo test if account_id in card
        account.setCard(card);

        return AccountDtoMapper.convertEntityToCreationResponse(account);
    }

    @Override
    public void deleteById(long id) {
        Optional.ofNullable(accountRepository.findById(id))
                .orElseThrow(() -> new AccountWithIdNotFoundException(id));

        accountRepository.deleteById(id);
    }
}
