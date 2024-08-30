package by.grsu.liceum.dto.solved_activity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolvedActivityShortcutDto {
    private long uuid;
    private String activityName;
    private int rating;
    private Date timeOfSolving;
}
