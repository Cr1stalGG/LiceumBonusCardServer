package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.ticket.TicketShortcutDto;
import by.grsu.liceum.entity.Ticket;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TicketDtoMapper {
    public TicketShortcutDto convertEntityToShortcutDto(Ticket source){
        return TicketShortcutDto.builder()
                .uuid(source.getId())
                .bonusName(source.getBonus().getName())
                .code(source.getCode())
                .build();
    }
}
