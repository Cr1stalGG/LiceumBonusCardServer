package by.grsu.liceum.service;

import by.grsu.liceum.dto.account.AccountCreationDto;
import by.grsu.liceum.dto.account.AccountCreationResponse;
import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;

import java.util.List;

public interface AccountService {
    AccountFullDto findById(long id);
    List<AccountShortcutDto> findAll();
    AccountCreationResponse createUserWithRole(long adminId, AccountCreationDto creationDto);
    void deleteById(long id);
}
