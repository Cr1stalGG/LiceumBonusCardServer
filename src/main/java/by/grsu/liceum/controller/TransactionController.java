package by.grsu.liceum.controller;

import by.grsu.liceum.dto.transaction.TransactionDto;
import by.grsu.liceum.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/institutions/{institutionId}/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER', 'ROLE_SALE_UNIT')")
    public List<TransactionDto> findAllByInstitution(@PathVariable("institutionId") UUID institutionId){
        return transactionService.findAll(institutionId);
    }

    @GetMapping("/card/{cardId}")
    @PreAuthorize("isAuthenticated()")
    public List<TransactionDto> findAllByCardId(@PathVariable("institutionId") UUID institutionId, @PathVariable("cardId") UUID cardId){
        return transactionService.findAllByCardId(institutionId, cardId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public TransactionDto findById(@PathVariable("institutionId") UUID institutionId, @PathVariable("id") UUID id){
        return transactionService.findById(institutionId, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER')")
    public void deleteById(@PathVariable("institutionId") UUID institutionId, @PathVariable("id") UUID id){
        transactionService.deleteById(institutionId, id);
    }
}
