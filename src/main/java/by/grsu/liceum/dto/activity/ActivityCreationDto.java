package by.grsu.liceum.dto.activity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @Min(1)
    private int countOfMembers;
    @NotNull
    private UUID activityTypeId;
}
