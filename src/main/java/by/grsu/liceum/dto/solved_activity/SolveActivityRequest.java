package by.grsu.liceum.dto.solved_activity;

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
    private UUID activityId;
    private UUID accountId;
    private String code;
}
