package by.grsu.liceum.dto.account;

import by.grsu.liceum.dto.card.CardDto;
import by.grsu.liceum.dto.group.GroupShortcutDto;
import by.grsu.liceum.dto.image.ImageDto;
import by.grsu.liceum.dto.response.ResponseShortcutDto;
import by.grsu.liceum.dto.solved_activity.SolvedActivityShortcutDto;
import by.grsu.liceum.dto.ticket.TicketShortcutDto;
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
public class AccountFullDto {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phoneNumber;
    private int grade;
    private CardDto card;
    private ImageDto imageDto;
    private List<TicketShortcutDto> tickets;
    private List<GroupShortcutDto> ownedGroups;
    private List<GroupShortcutDto> otherGroups;
    private List<SolvedActivityShortcutDto> solvedActivities;
    private List<ResponseShortcutDto> responses;
}
