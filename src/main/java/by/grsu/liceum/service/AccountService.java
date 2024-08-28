package by.grsu.liceum.service;

import by.grsu.liceum.dto.account.AccountCreationDto;
import by.grsu.liceum.dto.account.AccountCreationResponse;
import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;

import java.util.List;

public interface AccountService {
    AccountFullDto findById(long institutionId, long id);
    List<AccountShortcutDto> findAll(long institutionId);
    AccountCreationResponse createUserWithRole(long institutionId, AccountCreationDto creationDto);
    void deleteById(long institutionId, long id);
}
