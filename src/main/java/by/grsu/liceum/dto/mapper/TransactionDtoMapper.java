package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.transaction.SentTransactionDto;
import by.grsu.liceum.dto.transaction.TakenTransactionDto;
import by.grsu.liceum.entity.Transaction;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class TransactionDtoMapper {
    public static TakenTransactionDto convertEntityToTakenDto(Transaction source){
        return Optional.ofNullable(source)
                .map(TransactionDtoMapper::buildTakenDto)
                .orElse(null);
    }

    public static SentTransactionDto convertEntityToSentDto(Transaction source){
        return Optional.ofNullable(source)
                .map(TransactionDtoMapper::buildSentDto)
                .orElse(null);
    }

    private static TakenTransactionDto buildTakenDto(Transaction source) {
        return TakenTransactionDto.builder()
                .uuid(source.getId())
                .balance(source.getBalance())
                .fromCardNumber(source.getFromCard().getNumber())
                .timeOfTransaction(source.getTimeOfTransaction())
                .build();
    }

    private static SentTransactionDto buildSentDto(Transaction source) {
        return SentTransactionDto.builder()
                .uuid(source.getId())
                .balance(source.getBalance())
                .toCardNumber(source.getToCard().getNumber())
                .timeOfTransaction(source.getTimeOfTransaction())
                .build();
    }
}
