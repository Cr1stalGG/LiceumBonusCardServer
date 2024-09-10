package by.grsu.liceum.dto.card;

import by.grsu.liceum.dto.transaction.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto implements Serializable {
    private UUID uuid;
    private String number;
    private int balance;
    private List<TransactionDto> transactions;
}
