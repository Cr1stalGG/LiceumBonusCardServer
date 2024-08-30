package by.grsu.liceum.dto.solved_activity;

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
public class SolveActivityRequest {
    private long activityId;
    private long accountId;
    private String code;
}
