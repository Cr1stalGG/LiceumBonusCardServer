package by.grsu.liceum.service;

import by.grsu.liceum.dto.bonus.BonusFullDto;
import by.grsu.liceum.dto.bonus.BonusShortcutDto;

import java.util.List;

public interface BonusService {
    List<BonusShortcutDto> findAll();
    BonusFullDto findById(long id);

}
