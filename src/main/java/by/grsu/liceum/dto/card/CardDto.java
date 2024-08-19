package by.grsu.liceum.dto.card;

import by.grsu.liceum.dto.transaction.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private long uuid;
    private String number;
    private int balance;
    private List<TransactionDto> transactions;
}
