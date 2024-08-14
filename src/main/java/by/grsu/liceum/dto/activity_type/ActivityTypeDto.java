package by.grsu.liceum.dto.activity_type;

import by.grsu.liceum.dto.activity.ActivityShortcutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityTypeDto {
    private long uuid;
    private String name;
    private int cost;
    private List<ActivityShortcutDto> activities;
}
