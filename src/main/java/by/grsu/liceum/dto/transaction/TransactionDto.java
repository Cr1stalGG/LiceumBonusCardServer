package by.grsu.liceum.dto.transaction;

import by.grsu.liceum.dto.status.StatusDto;
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
public class TransactionDto {
    private long uuid;
    private int balance;
    private String cardNumber;
    private StatusDto status;
    private Date timeOfTransaction;
}
