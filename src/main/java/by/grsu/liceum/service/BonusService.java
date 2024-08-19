package by.grsu.liceum.service;

import by.grsu.liceum.dto.bonus.BonusBuyDto;
import by.grsu.liceum.dto.bonus.BonusCreationDto;
import by.grsu.liceum.dto.bonus.BonusFullDto;
import by.grsu.liceum.dto.bonus.BonusShortcutDto;
import by.grsu.liceum.dto.ticket.TicketFullDto;

import java.util.List;

public interface BonusService {
    List<BonusShortcutDto> findAll();
    BonusFullDto findById(long id);
    BonusFullDto createBonus(BonusCreationDto creationDto);
    TicketFullDto buyBonus(BonusBuyDto buyDto);
    void deleteById(long id);
}
