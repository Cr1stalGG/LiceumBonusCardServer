package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.bonus.BonusShortcutDto;
import by.grsu.liceum.dto.ticket.TicketFullDto;
import by.grsu.liceum.dto.ticket.TicketShortcutDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Bonus;
import by.grsu.liceum.entity.Ticket;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class TicketDtoMapper {
    public static TicketShortcutDto convertEntityToShortcutDto(Ticket source) {
        return Optional.ofNullable(source)
                .map(TicketDtoMapper::buildShortcutDto)
                .orElse(null);
    }

    public static TicketFullDto convertEntitToFullDto(Ticket source){
        return Optional.ofNullable(source)
                .map(TicketDtoMapper::buildFullDto)
                .orElse(null);
    }

    private static TicketFullDto buildFullDto(Ticket source) {
        return TicketFullDto.builder()
                .uuid(source.getId())
                .code(source.getCode())
                .owner(buildAccountDto(source.getAccount()))
                .bonus(buildBonusDto(source.getBonus()))
                .build();
    }

    private static BonusShortcutDto buildBonusDto(Bonus source) {
        return Optional.ofNullable(source)
                .map(BonusDtoMapper::convertEntityToShortcutDto)
                .orElse(null);
    }

    private static AccountShortcutDto buildAccountDto(Account source) {
        return Optional.ofNullable(source)
                .map(AccountDtoMapper::convertEntityToShortcutDto)
                .orElse(null);
    }

    private static TicketShortcutDto buildShortcutDto(Ticket source){
        return TicketShortcutDto.builder()
                .uuid(source.getId())
                .bonusName(source.getBonus().getName())
                .code(source.getCode())
                .build();
    }

}
