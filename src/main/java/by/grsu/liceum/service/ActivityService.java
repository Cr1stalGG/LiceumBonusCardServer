package by.grsu.liceum.service;

import by.grsu.liceum.dto.activity.ActivityCreationDto;
import by.grsu.liceum.dto.activity.ActivityFullDto;
import by.grsu.liceum.dto.activity.ActivityShortcutDto;

import java.util.List;

public interface ActivityService {
    List<ActivityShortcutDto> findAll();
    ActivityFullDto findById(long id);
    ActivityFullDto createActivity(ActivityCreationDto creationDto);
    void deleteById(long id);
}
