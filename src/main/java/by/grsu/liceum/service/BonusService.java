package by.grsu.liceum.service;

import by.grsu.liceum.dto.bonus.BonusBuyDto;
import by.grsu.liceum.dto.bonus.BonusCreationDto;
import by.grsu.liceum.dto.bonus.BonusFullDto;
import by.grsu.liceum.dto.bonus.BonusShortcutDto;
import by.grsu.liceum.dto.ticket.TicketFullDto;

import java.util.List;
import java.util.UUID;

public interface BonusService {
    List<BonusShortcutDto> findAllByInstitutionId(UUID institutionId);
    BonusFullDto findById(UUID institutionId, UUID id);
    BonusFullDto createBonus(UUID institutionId, BonusCreationDto creationDto);
    TicketFullDto buyBonus(UUID institutionId, BonusBuyDto buyDto);
    void deleteById(UUID institutionId, UUID id);
}
