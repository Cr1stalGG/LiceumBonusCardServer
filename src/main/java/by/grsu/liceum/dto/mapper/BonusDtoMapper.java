package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.bonus.BonusCreationDto;
import by.grsu.liceum.dto.bonus.BonusFullDto;
import by.grsu.liceum.dto.bonus.BonusShortcutDto;
import by.grsu.liceum.entity.Bonus;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Optional;

@UtilityClass
public class BonusDtoMapper {
    public static BonusFullDto convertEntityToFullDto(Bonus source) {
        return Optional.ofNullable(source)
                .map(BonusDtoMapper::buildFullDto)
                .orElse(null);
    }

    public static BonusShortcutDto convertEntityToShortcutDto(Bonus source){
        return Optional.ofNullable(source)
                .map(BonusDtoMapper::buildShortcutDto)
                .orElse(null);
    }

    public static Bonus convertDtoToEntity(BonusCreationDto source){
        return Optional.ofNullable(source)
                .map(BonusDtoMapper::buildEntity)
                .orElse(null);//todo mb exception
    }

    private static Bonus buildEntity(BonusCreationDto source) {
        return Bonus.builder()
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .count(source.getCount())
                .timeOfEnd(source.getTimeOfEnd())
                .tickets(new ArrayList<>())
                .build();
    }

    private static BonusShortcutDto buildShortcutDto(Bonus source) {
        return BonusShortcutDto.builder()
                .uuid(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .build();
    }

    private static BonusFullDto buildFullDto(Bonus source){
        return BonusFullDto.builder()
                .uuid(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .count(source.getCount())
                .timeOfEnd(source.getTimeOfEnd())
                .build();
    }
}
