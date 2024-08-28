package by.grsu.liceum.service;

import by.grsu.liceum.dto.activity.ActivityCreationDto;
import by.grsu.liceum.dto.activity.ActivityFullDto;
import by.grsu.liceum.dto.activity.ActivityShortcutDto;

import java.util.List;

public interface ActivityService {
    List<ActivityShortcutDto> findAll(long institutionId);
    ActivityFullDto findById(long institutionId, long id);
    ActivityFullDto createActivity(long institutionId, ActivityCreationDto creationDto);
    void deleteById(long institutionId, long id);
}
