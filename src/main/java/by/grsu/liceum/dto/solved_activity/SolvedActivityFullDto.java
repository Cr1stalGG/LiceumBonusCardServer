package by.grsu.liceum.dto.solved_activity;

import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.activity.ActivityShortcutDto;
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
public class SolvedActivityFullDto {
    private long uuid;
    private AccountShortcutDto account;
    private ActivityShortcutDto activity;
    private String code;
    private Date timeOfSolving;
}
