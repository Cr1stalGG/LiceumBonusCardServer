package by.grsu.liceum.service;

import by.grsu.liceum.dto.transaction.TransactionCreationDto;
import by.grsu.liceum.dto.transaction.TransactionDto;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> findAll();
    List<TransactionDto> findAllByCardId(long cardId);
    TransactionDto findById(long id);
    TransactionDto createTransaction(TransactionCreationDto creationDto);
    void deleteById(long id);
}
