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
public class TicketShortcutDto {
    private long uuid;
    private String bonusName;
    private String code;
}
