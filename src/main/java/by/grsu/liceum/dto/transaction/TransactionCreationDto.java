package by.grsu.liceum.dto.transaction;

import by.grsu.liceum.entity.enums.StatusConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreationDto {
    private long cardId;
    private int balance;
    private StatusConstant status;
}
