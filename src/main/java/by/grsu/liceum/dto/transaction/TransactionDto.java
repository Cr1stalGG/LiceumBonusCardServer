package by.grsu.liceum.dto.transaction;

import by.grsu.liceum.dto.status.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto implements Serializable {
    private UUID uuid;
    private int balance;
    private String cardNumber;
    private StatusDto status;
    private Date timeOfTransaction;
}
