package by.grsu.liceum.dto.group;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class GroupCreationDto {
    @NotBlank
    @Size(max = 60)
    private String name;
    @NotNull
    private UUID adminId;
    private List<UUID> membersId;//todo optional
}
