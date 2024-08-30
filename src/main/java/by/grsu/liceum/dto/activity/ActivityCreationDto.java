package by.grsu.liceum.dto.activity;

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
public class ActivityCreationDto {
    private String name;
    private String description;
    private int countOfMembers;
    private UUID activityTypeId;
}
