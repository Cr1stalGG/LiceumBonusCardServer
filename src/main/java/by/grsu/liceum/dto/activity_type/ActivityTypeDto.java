package by.grsu.liceum.dto.activity_type;

import by.grsu.liceum.dto.activity.ActivityShortcutDto;
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
public class ActivityTypeDto {
    private UUID uuid;
    private String name;
    private int cost;
    private List<ActivityShortcutDto> activities;
}
