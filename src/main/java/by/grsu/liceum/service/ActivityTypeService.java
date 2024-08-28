package by.grsu.liceum.service;

import by.grsu.liceum.dto.activity_type.ActivityTypeCreationDto;
import by.grsu.liceum.dto.activity_type.ActivityTypeDto;

import java.util.List;

public interface ActivityTypeService {
    ActivityTypeDto findById(long institutionId, long id);
    List<ActivityTypeDto> findAll(long institutionId);
    ActivityTypeDto save(long institutionId, ActivityTypeCreationDto creationDto);
    void deleteById(long institutionId, long id);
}
