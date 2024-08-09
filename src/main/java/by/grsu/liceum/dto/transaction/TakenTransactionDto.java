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
public class TakenTransactionDto {
    private long uuid;
    private int balance;
    private String fromCardNumber; //todo optional switch to ownerName
    private Date timeOfTransaction;
}
