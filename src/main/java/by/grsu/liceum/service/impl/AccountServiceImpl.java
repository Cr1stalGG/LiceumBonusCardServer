package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.mapper.AccountDtoMapper;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.repository.AccountRepository;
import by.grsu.liceum.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

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
    public void deleteById(long id) {
        Optional.ofNullable(accountRepository.findById(id))
                .orElseThrow(() -> new AccountWithIdNotFoundException(id));

        accountRepository.deleteById(id);
    }
}
