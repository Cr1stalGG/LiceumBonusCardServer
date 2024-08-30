package by.grsu.liceum.service;

import by.grsu.liceum.dto.activity.ActivityCreationDto;
import by.grsu.liceum.dto.activity.ActivityFullDto;
import by.grsu.liceum.dto.activity.ActivityShortcutDto;

import java.util.List;
import java.util.UUID;

public interface ActivityService {
    List<ActivityShortcutDto> findAll(UUID institutionId);
    ActivityFullDto findById(UUID institutionId, UUID id);
    ActivityFullDto createActivity(UUID institutionId, ActivityCreationDto creationDto);
    void deleteById(UUID institutionId, UUID id);
}
