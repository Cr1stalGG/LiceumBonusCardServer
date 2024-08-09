package by.grsu.liceum.service;

import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;

import java.util.List;

public interface AccountService {
    AccountFullDto findById(long id);
    List<AccountShortcutDto> findAll();
    void deleteById(long id);
}
