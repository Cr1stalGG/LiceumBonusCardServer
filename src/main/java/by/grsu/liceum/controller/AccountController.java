package by.grsu.liceum.controller;

import by.grsu.liceum.dto.account.AccountCreationDto;
import by.grsu.liceum.dto.account.AccountCreationResponse;
import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/institutions/{institutionId}/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public AccountFullDto findById(@PathVariable("institutionId") long institutionId, @PathVariable("id") long id){
        return accountService.findById(institutionId, id);
    }

    @GetMapping
    @Secured(value = "ROLE_SADMIN")
    public List<AccountShortcutDto> findAll(@PathVariable("institutionId") long institutionId){
        return accountService.findAll(institutionId);
    }

    @PostMapping
    @Secured(value = "ROLE_ADMIN")
    public AccountCreationResponse createAccountWithRole(@PathVariable("institutionId") long institutionId, @RequestBody AccountCreationDto creationDto){
        return accountService.createUserWithRole(institutionId, creationDto);
    }

    @DeleteMapping("/{id}")
    @Secured(value = "ROLE_ADMIN")
    public void deleteAccountById(@PathVariable("institutionId") long institutionId, @PathVariable("id") long id){
        accountService.deleteById(institutionId, id);
    }
}
