package by.grsu.liceum.dto.ticket;

import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.bonus.BonusShortcutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketFullDto {
    private long uuid;
    private String code;
    private AccountShortcutDto owner;
    private BonusShortcutDto bonus;
}