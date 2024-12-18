package by.grsu.liceum.dto.response_status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatusDto implements Serializable {
    private UUID uuid;
    private String name;
    private String description;
}
