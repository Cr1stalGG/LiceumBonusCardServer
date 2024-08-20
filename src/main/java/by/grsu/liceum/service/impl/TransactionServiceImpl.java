package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.mapper.TransactionDtoMapper;
import by.grsu.liceum.dto.transaction.TransactionCreationDto;
import by.grsu.liceum.dto.transaction.TransactionDto;
import by.grsu.liceum.entity.Card;
import by.grsu.liceum.entity.Transaction;
import by.grsu.liceum.exception.CardWithIdNotFoundException;
import by.grsu.liceum.exception.TransactionWithIdNotFoundException;
import by.grsu.liceum.repository.CardRepository;
import by.grsu.liceum.repository.StatusRepository;
import by.grsu.liceum.repository.TransactionRepository;
import by.grsu.liceum.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        return card.getTransactions().stream()//todo mb optional
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
        return null;
    }

    @Override
    public void deleteById(long id) { //mb return optional to better perfomence
        findById(id);

        transactionRepository.deleteById(id);
    }
}
