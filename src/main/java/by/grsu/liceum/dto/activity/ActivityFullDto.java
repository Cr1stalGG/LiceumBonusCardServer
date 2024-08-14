package by.grsu.liceum.dto.activity;

import by.grsu.liceum.dto.activity_type.ActivityTypeShortcutDto;
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
public class ActivityFullDto {
    private long uuid;
    private String name;
    private String description;
    private int countOfMembers;
    private ActivityTypeShortcutDto activityType;
}
