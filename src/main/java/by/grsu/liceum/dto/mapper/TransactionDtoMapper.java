package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.status.StatusDto;
import by.grsu.liceum.dto.transaction.TransactionDto;
import by.grsu.liceum.entity.Status;
import by.grsu.liceum.entity.Transaction;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class TransactionDtoMapper {
    public static TransactionDto convertEntityToDto(Transaction source){
        return Optional.ofNullable(source)
                .map(TransactionDtoMapper::buildDto)
                .orElse(null);
    }

    private static TransactionDto buildDto(Transaction source) {
        return TransactionDto.builder()
                .uuid(source.getId())
                .balance(source.getBalance())
                .status(buildStatus(source.getStatus()))
                .timeOfTransaction(source.getTimeOfTransaction())
                .build();
    }

    private static StatusDto buildStatus(Status source){
        return Optional.ofNullable(source)
                .map(StatusDtoMapper::convertEntityToDto)
                .orElse(null);
    }
}
