package by.grsu.liceum.service;

import by.grsu.liceum.dto.transaction.TransactionCreationDto;
import by.grsu.liceum.dto.transaction.TransactionDto;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    List<TransactionDto> findAll(UUID institutionId);
    List<TransactionDto> findAllByCardId(UUID institutionId, UUID cardId);
    TransactionDto findById(UUID institutionId, UUID id);
    TransactionDto createTransaction(UUID institutionId, TransactionCreationDto creationDto);
    void deleteById(UUID institutionId, UUID id);
}
