package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.activity.ActivityCreationDto;
import by.grsu.liceum.dto.activity.ActivityFullDto;
import by.grsu.liceum.dto.activity.ActivityShortcutDto;
import by.grsu.liceum.entity.Activity;
import by.grsu.liceum.utils.Generator;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class ActivityDtoMapper {
    public static ActivityShortcutDto convertEntityToShortcutDto(Activity source) {
        return Optional.ofNullable(source)
                .map(ActivityDtoMapper::buildShortcutDto)
                .orElse(null);
    }

    public static ActivityFullDto convertEntityToFullDto(Activity source){
        return Optional.ofNullable(source)
                .map(ActivityDtoMapper::buildFullDto)
                .orElse(null);
    }

    public static Activity convertDtoToEntity(ActivityCreationDto source) {
        return Optional.ofNullable(source)
                .map(ActivityDtoMapper::buildEntity)
                .orElse(null);
    }

    private static Activity buildEntity(ActivityCreationDto source) {
        return Activity.builder()
                .name(source.getName())
                .description(source.getDescription())
                .countOfMembers(source.getCountOfMembers())
                .build();
    }

    private static ActivityFullDto buildFullDto(Activity source) {
        return ActivityFullDto.builder()
                .uuid(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .countOfMembers(source.getCountOfMembers())
                .activityType(ActivityTypeDtoMapper.convertEntityToShortCutDto(source.getActivityType()))
                .build();
    }

    private static ActivityShortcutDto buildShortcutDto(Activity source) {
        return ActivityShortcutDto.builder()
                .uuid(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }
}
