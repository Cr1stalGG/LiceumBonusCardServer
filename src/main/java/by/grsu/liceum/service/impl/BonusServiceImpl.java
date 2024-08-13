package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.bonus.BonusFullDto;
import by.grsu.liceum.dto.bonus.BonusShortcutDto;
import by.grsu.liceum.dto.mapper.BonusDtoMapper;
import by.grsu.liceum.entity.Bonus;
import by.grsu.liceum.exception.BonusWithIdNotFoundException;
import by.grsu.liceum.repository.BonusRepository;
import by.grsu.liceum.service.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BonusServiceImpl implements BonusService {
    private final BonusRepository bonusRepository;

    @Override
    public List<BonusShortcutDto> findAll() {
        return bonusRepository.findAll().stream()
                .map(BonusDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    public BonusFullDto findById(long id) {
        Bonus bonus = Optional.ofNullable(bonusRepository.findById(id))
                .orElseThrow(() -> new BonusWithIdNotFoundException(id));

        return BonusDtoMapper.convertEntityToFullDto(bonus);
    }
}
