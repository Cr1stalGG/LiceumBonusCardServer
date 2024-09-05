package by.grsu.liceum.controller;

import by.grsu.liceum.dto.transaction.TransactionDto;
import by.grsu.liceum.service.AccountService;
import by.grsu.liceum.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/institutions/{institutionId}/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final AccountService accountService;

    @PostMapping("/push/{accountId}/{value}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public TransactionDto addRating(@PathVariable("institutionId") UUID institutionId, @PathVariable("accountId") UUID accountId, @PathVariable("value") int value){
        return adminService.addRating(institutionId, accountId, value);
    }

    @PostMapping("/take/{accountId}/{value}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public TransactionDto getRating(@PathVariable("institutionId") UUID institutionId, @PathVariable("accountId") UUID accountId, @PathVariable("value") int value){
        return adminService.getRating(institutionId, accountId, value);
    }


}
