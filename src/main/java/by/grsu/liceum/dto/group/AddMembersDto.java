package by.grsu.liceum.dto.group;

import jakarta.validation.constraints.NotNull;
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
public class AddMembersDto {
    @NotNull
    private UUID adminId;
    @NotNull
    private UUID groupId;
    @NotNull
    private List<@NotNull UUID> membersId;
}
