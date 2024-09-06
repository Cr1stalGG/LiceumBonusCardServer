package by.grsu.liceum.dto.solved_activity;

import jakarta.validation.constraints.NotBlank;
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
public class SolveActivityRequest {
    @NotNull
    private UUID activityId;
    @NotNull
    private UUID accountId;
    @NotBlank
    private String code;
}
