package by.grsu.liceum.dto.bonus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BonusBuyDto {
    @NotNull
    private UUID accountId;
    @NotNull
    private UUID bonusId;
}
