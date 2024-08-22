package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.mapper.TransactionDtoMapper;
import by.grsu.liceum.dto.transaction.TransactionCreationDto;
import by.grsu.liceum.dto.transaction.TransactionDto;
import by.grsu.liceum.entity.Card;
import by.grsu.liceum.entity.Status;
import by.grsu.liceum.entity.Transaction;
import by.grsu.liceum.exception.CardWithIdNotFoundException;
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

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final StatusRepository statusRepository;

    @Override
    public List<TransactionDto> findAll() {
        return transactionRepository.findAll().stream()
                .map(TransactionDtoMapper::convertEntityToDto)
                .toList();
    }

    @Override
    public List<TransactionDto> findAllByCardId(long cardId) {
        Card card = Optional.ofNullable(cardRepository.findById(cardId))
                .orElseThrow(() -> new CardWithIdNotFoundException(cardId));

        return card.getTransactions().stream()
                .map(TransactionDtoMapper::convertEntityToDto)
                .toList();
    }

    @Override
    public TransactionDto findById(long id) {
        Transaction transaction = Optional.ofNullable(transactionRepository.findById(id))
                .orElseThrow(() -> new TransactionWithIdNotFoundException(id));

        return TransactionDtoMapper.convertEntityToDto(transaction);
    }

    @Override
    @Transactional
    public TransactionDto createTransaction(TransactionCreationDto creationDto) {
        Card card = Optional.ofNullable(cardRepository.findById(creationDto.getCardId()))
                .orElseThrow(() -> new CardWithIdNotFoundException(creationDto.getCardId()));

        Status status = Optional.ofNullable(statusRepository.findByName(creationDto.getStatus()))
                .orElseThrow(() -> new InvalidTransactionStatusException(creationDto.getStatus().name()));

        Transaction transaction = Transaction.builder()
                .balance(creationDto.getBalance())
                .card(card)
                .status(status)
                .timeOfTransaction(new Date(System.currentTimeMillis()))
                .build();

        transactionRepository.save(transaction);

        card.getTransactions().add(transaction);
        status.getTransactions().add(transaction);

        return TransactionDtoMapper.convertEntityToDto(transaction);
    }

    @Override
    public void deleteById(long id) {
        findById(id);

        transactionRepository.deleteById(id);
    }
}
