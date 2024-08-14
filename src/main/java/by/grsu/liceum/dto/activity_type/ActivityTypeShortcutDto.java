package by.grsu.liceum.dto.activity_type;

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
public class ActivityTypeShortcutDto {
    private long uuid;
    private String name;
    private int cost;
}
