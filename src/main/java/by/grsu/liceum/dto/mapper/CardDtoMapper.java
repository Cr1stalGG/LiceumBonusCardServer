package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.card.CardDto;
import by.grsu.liceum.dto.transaction.SentTransactionDto;
import by.grsu.liceum.dto.transaction.TakenTransactionDto;
import by.grsu.liceum.entity.Card;
import by.grsu.liceum.entity.Transaction;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class CardDtoMapper {
    public static CardDto convertEntityToDto(Card source){
        return Optional.ofNullable(source)
                .map(CardDtoMapper::buildDto)
                .orElse(null);
    }

    private static CardDto buildDto(Card source) {
        return CardDto.builder()
                .uuid(source.getId())
                .number(source.getNumber())
                .balance(source.getBalance())
                .sentTransactions(buildSentTransactions(source.getSentTransactions()))
                .takenTransactions(buildTakenTransactions(source.getTakenTransactions()))
                .build();
    }

    private static List<TakenTransactionDto> buildTakenTransactions(List<Transaction> source) {
        if(source == null)
            return new ArrayList<>();

        return source.stream()
                .map(TransactionDtoMapper::convertEntityToTakenDto)
                .toList();
    }

    private static List<SentTransactionDto> buildSentTransactions(List<Transaction> source) {
        if(source == null)
            return new ArrayList<>();

        return source.stream()
                .map(TransactionDtoMapper::convertEntityToSentDto)
                .toList();
    }
}
