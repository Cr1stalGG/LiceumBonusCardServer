package by.grsu.liceum.controller;

import by.grsu.liceum.dto.transaction.TransactionDto;
import by.grsu.liceum.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public List<TransactionDto> findAll(){
        return transactionService.findAll();
    }

    @GetMapping("/card/{cardId}")
    public List<TransactionDto> findAllByCardId(@PathVariable("cardId") long cardId){
        return transactionService.findAllByCardId(cardId);
    }

    @GetMapping("/{id}")
    public TransactionDto findById(@PathVariable("id") long id){
        return transactionService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        transactionService.deleteById(id);
    }
}
