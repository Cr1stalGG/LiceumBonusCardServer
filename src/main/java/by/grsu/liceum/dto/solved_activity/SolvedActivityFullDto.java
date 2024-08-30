package by.grsu.liceum.dto.solved_activity;

import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.activity.ActivityShortcutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolvedActivityFullDto {
    private UUID uuid;
    private AccountShortcutDto account;
    private ActivityShortcutDto activity;
    private Date timeOfSolving;
}
