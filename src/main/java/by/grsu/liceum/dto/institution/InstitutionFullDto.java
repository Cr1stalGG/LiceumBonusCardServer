package by.grsu.liceum.dto.institution;

import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.activity_type.ActivityTypeShortcutDto;
import by.grsu.liceum.dto.bonus.BonusShortcutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionFullDto {
    private UUID uuid;
    private String name;
    private String city;
    private List<AccountShortcutDto> accounts;
    private List<ActivityTypeShortcutDto> activityTypes;
    private List<BonusShortcutDto> bonuses;
}
