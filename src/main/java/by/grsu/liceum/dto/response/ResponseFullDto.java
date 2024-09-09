package by.grsu.liceum.dto.response;

import by.grsu.liceum.dto.response_status.ResponseStatusDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFullDto {
    private UUID uuid;
    private String message;
    private ResponseStatusDto responseStatusDto;
    private Date timeOfResponse;
}
