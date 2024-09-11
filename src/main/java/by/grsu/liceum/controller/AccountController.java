package by.grsu.liceum.controller;

import by.grsu.liceum.dto.account.AccountCreationDto;
import by.grsu.liceum.dto.account.AccountCreationResponse;
import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.image.ImageCreationDto;
import by.grsu.liceum.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/institutions/{institutionId}/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public AccountFullDto findById(@PathVariable("institutionId") UUID institutionId, @PathVariable("id") UUID id){
        return accountService.findById(institutionId, id);
    }

    @GetMapping
    @Cacheable(value = "accounts", key = "#institutionId")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_HEAD_TEACHER', 'ROLE_SALE_UNIT', 'ROLE_TEACHER')")
    public List<AccountShortcutDto> findAll(@PathVariable("institutionId") UUID institutionId){
        return accountService.findAll(institutionId);
    }

    @PostMapping
    @CacheEvict(value = "accounts", key = "#institutionId", allEntries = true)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public AccountCreationResponse createAccountWithRole(@PathVariable("institutionId") UUID institutionId, @RequestBody @Valid AccountCreationDto creationDto){
        return accountService.createUserWithRole(institutionId, creationDto);
    }

    @PutMapping("/{accountId}/regenerate/password")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public AccountCreationResponse regeneratePassword(@PathVariable("institutionId") UUID institutionId, @PathVariable("accountId") UUID accountId){
        return accountService.regeneratePassword(institutionId, accountId);
    }

    @PutMapping("/{accountId}/images")
    @CacheEvict(value = "accounts", key = "#institutionId", allEntries = true)
    @PreAuthorize("isAuthenticated()")
    public AccountFullDto updateImage(@PathVariable("institutionId") UUID institutionId, @PathVariable("accountId") UUID accountId, @RequestBody @Valid ImageCreationDto imageCreationDto){
        return accountService.setImage(institutionId, accountId, imageCreationDto); //todo null
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "accounts", key = "#institutionId", allEntries = true)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public void deleteAccountById(@PathVariable("institutionId") UUID institutionId, @PathVariable("id") UUID id){
        accountService.deleteById(institutionId, id);
    }
}
