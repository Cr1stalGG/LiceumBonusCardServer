package by.grsu.liceum.service;

import by.grsu.liceum.dto.activity_type.ActivityTypeCreationDto;
import by.grsu.liceum.dto.activity_type.ActivityTypeDto;

import java.util.List;

public interface ActivityTypeService {
    ActivityTypeDto findById(long id);
    List<ActivityTypeDto> findAll();
    ActivityTypeDto save(ActivityTypeCreationDto creationDto);
    void deleteById(long id);
}
