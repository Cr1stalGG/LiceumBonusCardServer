package by.grsu.liceum.service;

import by.grsu.liceum.dto.transaction.TransactionCreationDto;
import by.grsu.liceum.dto.transaction.TransactionDto;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> findAll(long institutionId);
    List<TransactionDto> findAllByCardId(long institutionId, long cardId);
    TransactionDto findById(long institutionId, long id);
    TransactionDto createTransaction(long institutionId, TransactionCreationDto creationDto);
    void deleteById(long institutionId, long id);
}
