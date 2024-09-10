package by.grsu.liceum.dto.solved_activity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolvedActivityShortcutDto implements Serializable {
    private UUID uuid;
    private String activityName;
    private int rating;
    private Date timeOfSolving;
}
