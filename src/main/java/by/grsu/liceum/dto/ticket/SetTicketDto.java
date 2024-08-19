package by.grsu.liceum.dto.ticket;

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
public class SetTicketDto {
    private long accountId;
    private long bonusId;
}
