package by.grsu.liceum.dto.group;

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
    private String name;
    private UUID adminId;
    private List<UUID> membersId;//todo optional
}
