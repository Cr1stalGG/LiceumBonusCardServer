package by.grsu.liceum.service;

import by.grsu.liceum.dto.account.AccountCreationDto;
import by.grsu.liceum.dto.account.AccountCreationResponse;
import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountFullDto findById(UUID institutionId, UUID id);
    List<AccountShortcutDto> findAll(UUID institutionId);
    AccountCreationResponse createUserWithRole(UUID institutionId, AccountCreationDto creationDto);
    AccountCreationResponse regeneratePassword(UUID institutionId, UUID accountId);
    void deleteById(UUID institutionId, UUID id);
}
