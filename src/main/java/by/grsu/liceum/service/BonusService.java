package by.grsu.liceum.service;

import by.grsu.liceum.dto.bonus.BonusBuyDto;
import by.grsu.liceum.dto.bonus.BonusCreationDto;
import by.grsu.liceum.dto.bonus.BonusFullDto;
import by.grsu.liceum.dto.bonus.BonusShortcutDto;
import by.grsu.liceum.dto.ticket.TicketFullDto;

import java.util.List;

public interface BonusService {
    List<BonusShortcutDto> findAllByInstitutionId(long institutionId);
    BonusFullDto findById(long institutionId, long id);
    BonusFullDto createBonus(long institutionId, BonusCreationDto creationDto);
    TicketFullDto buyBonus(long institutionId, BonusBuyDto buyDto);
    void deleteById(long institutionId, long id);
}
