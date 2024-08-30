package by.grsu.liceum.service;

import by.grsu.liceum.dto.activity_type.ActivityTypeCreationDto;
import by.grsu.liceum.dto.activity_type.ActivityTypeDto;

import java.util.List;
import java.util.UUID;

public interface ActivityTypeService {
    ActivityTypeDto findById(UUID institutionId, UUID id);
    List<ActivityTypeDto> findAll(UUID institutionId);
    ActivityTypeDto save(UUID institutionId, ActivityTypeCreationDto creationDto);
    void deleteById(UUID institutionId, UUID id);
}
