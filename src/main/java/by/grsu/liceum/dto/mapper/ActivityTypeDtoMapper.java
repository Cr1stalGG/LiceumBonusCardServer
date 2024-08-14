package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.activity.ActivityShortcutDto;
import by.grsu.liceum.dto.activity_type.ActivityTypeCreationDto;
import by.grsu.liceum.dto.activity_type.ActivityTypeDto;
import by.grsu.liceum.dto.activity_type.ActivityTypeShortcutDto;
import by.grsu.liceum.entity.Activity;
import by.grsu.liceum.entity.ActivityType;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class ActivityTypeDtoMapper {
    public static ActivityTypeDto convertEntityToDto(ActivityType source){
        return Optional.ofNullable(source)
                .map(ActivityTypeDtoMapper::buildDto)
                .orElse(null);
    }

    public static ActivityTypeShortcutDto convertEntityToShortCutDto(ActivityType source) {
        return Optional.ofNullable(source)
                .map(ActivityTypeDtoMapper::buildShortcutDto)
                .orElse(null);
    }

    public static ActivityType convertDtoToEntity(ActivityTypeCreationDto source){
        return Optional.ofNullable(source)
                .map(ActivityTypeDtoMapper::buildEntity)
                .orElse(null);
    }

    private static ActivityType buildEntity(ActivityTypeCreationDto source) {
        return ActivityType.builder()
                .name(source.getName())
                .cost(source.getCost())
                .build();
    }

    private static ActivityTypeShortcutDto buildShortcutDto(ActivityType source) {
        return ActivityTypeShortcutDto.builder()
                .uuid(source.getId())
                .name(source.getName())
                .cost(source.getCost())
                .build();
    }

    private static ActivityTypeDto buildDto(ActivityType source) {
        return ActivityTypeDto.builder()
                .uuid(source.getId())
                .name(source.getName())
                .cost(source.getCost())
                .build();
    }

    private static List<ActivityShortcutDto> buildActivityDtos(List<Activity> source){
        if(source == null)
            return new ArrayList<>();

        return  source.stream()
                .map(ActivityDtoMapper::convertEntityToShortcutDto)
                .toList();
    }
}
