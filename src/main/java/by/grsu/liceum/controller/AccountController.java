package by.grsu.liceum.controller;

import by.grsu.liceum.dto.account.AccountCreationDto;
import by.grsu.liceum.dto.account.AccountCreationResponse;
import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public AccountFullDto findById(@PathVariable("id") long id){
        return accountService.findById(id);
    }

    @GetMapping
    public List<AccountShortcutDto> findAll(){
        return accountService.findAll();
    }

    @PostMapping
    public AccountCreationResponse createAccountWithRole(@RequestBody AccountCreationDto creationDto){
        return accountService.createUserWithRole(creationDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        accountService.deleteById(id);
    }
}
