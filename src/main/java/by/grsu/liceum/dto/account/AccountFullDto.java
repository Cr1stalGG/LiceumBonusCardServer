package by.grsu.liceum.dto.account;

import by.grsu.liceum.dto.card.CardDto;
import by.grsu.liceum.dto.group.GroupShortcutDto;
import by.grsu.liceum.dto.ticket.TicketShortcutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountFullDto {
    private long uuid;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phoneNumber;
    private CardDto card;
    private List<TicketShortcutDto> tickets;
    private List<GroupShortcutDto> ownedGroups;
    private List<GroupShortcutDto> otherGroups;
}
