package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.mapper.TransactionDtoMapper;
import by.grsu.liceum.dto.transaction.TransactionCreationDto;
import by.grsu.liceum.dto.transaction.TransactionDto;
import by.grsu.liceum.entity.Card;
import by.grsu.liceum.entity.TransactionStatus;
import by.grsu.liceum.entity.Transaction;
import by.grsu.liceum.exception.CardWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidPermissionsException;
import by.grsu.liceum.exception.InvalidTransactionStatusException;
import by.grsu.liceum.exception.TransactionWithIdNotFoundException;
import by.grsu.liceum.repository.CardRepository;
import by.grsu.liceum.repository.StatusRepository;
import by.grsu.liceum.repository.TransactionRepository;
import by.grsu.liceum.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final StatusRepository statusRepository;

    @Override
    public List<TransactionDto> findAll(UUID institutionId) {
        return transactionRepository.findAllByCard_Account_Institution_Id(institutionId).stream()
                .map(TransactionDtoMapper::convertEntityToDto)
                .toList();
    }

    @Override
    public List<TransactionDto> findAllByCardId(UUID institutionId, UUID cardId) {
        Card card = Optional.ofNullable(cardRepository.findById(cardId))
                .orElseThrow(() -> new CardWithIdNotFoundException(cardId));

        if(!card.getAccount().getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        return card.getTransactions().stream()
                .map(TransactionDtoMapper::convertEntityToDto)
                .toList();
    }

    @Override
    public TransactionDto findById(UUID institutionId, UUID id) {
        Transaction transaction = Optional.ofNullable(transactionRepository.findById(id))
                .orElseThrow(() -> new TransactionWithIdNotFoundException(id));

        if(!transaction.getCard().getAccount().getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        return TransactionDtoMapper.convertEntityToDto(transaction);
    }

    @Override
    @Transactional
    public TransactionDto createTransaction(UUID institutionId, TransactionCreationDto creationDto) {
        Card card = Optional.ofNullable(cardRepository.findById(creationDto.getCardId()))
                .orElseThrow(() -> new CardWithIdNotFoundException(creationDto.getCardId()));

        if(!card.getAccount().getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        TransactionStatus status = Optional.ofNullable(statusRepository.findByName(creationDto.getStatus()))
                .orElseThrow(() -> new InvalidTransactionStatusException(creationDto.getStatus()));

        Transaction transaction = Transaction.builder()
                .balance(creationDto.getBalance())
                .card(card)
                .transactionStatus(status)
                .timeOfTransaction(new Date(System.currentTimeMillis()))
                .build();

        transactionRepository.save(transaction);

        card.getTransactions().add(transaction);
        status.getTransactions().add(transaction);

        return TransactionDtoMapper.convertEntityToDto(transaction);
    }

    @Override
    public void deleteById(UUID institutionId, UUID id) {
        Transaction transaction = Optional.ofNullable(transactionRepository.findById(id))
                .orElseThrow(() -> new TransactionWithIdNotFoundException(id));

        if(!transaction.getCard().getAccount().getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        transactionRepository.deleteById(id);
    }
}
