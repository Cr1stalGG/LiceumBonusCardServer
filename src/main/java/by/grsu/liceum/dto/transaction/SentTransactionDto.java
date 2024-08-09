package by.grsu.liceum.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SentTransactionDto {
    private long uuid;
    private int balance;
    private String toCardNumber;
    private Date timeOfTransaction;
}
