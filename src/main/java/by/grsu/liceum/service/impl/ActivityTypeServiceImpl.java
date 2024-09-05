package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.activity_type.ActivityTypeCreationDto;
import by.grsu.liceum.dto.activity_type.ActivityTypeDto;
import by.grsu.liceum.dto.mapper.ActivityTypeDtoMapper;
import by.grsu.liceum.entity.ActivityType;
import by.grsu.liceum.entity.Institution;
import by.grsu.liceum.exception.ActivityTypeWithIdNotFoundException;
import by.grsu.liceum.exception.InstitutionWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidPermissionsException;
import by.grsu.liceum.repository.ActivityTypeRepository;
import by.grsu.liceum.repository.InstitutionRepository;
import by.grsu.liceum.service.ActivityTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivityTypeServiceImpl implements ActivityTypeService {
    private final ActivityTypeRepository activityTypeRepository;
    private final InstitutionRepository institutionRepository;

    @Override
    public ActivityTypeDto findById(UUID institutionId, UUID id) {
        ActivityType activityType = Optional.ofNullable(activityTypeRepository.findById(id))
                .orElseThrow(() -> new ActivityTypeWithIdNotFoundException(id));

        if(!activityType.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        return ActivityTypeDtoMapper.convertEntityToDto(activityType);
    }

    @Override
    public List<ActivityTypeDto> findAll(UUID institutionId) {
        return activityTypeRepository.findAllByInstitution_Id(institutionId).stream()
                .map(ActivityTypeDtoMapper::convertEntityToDto)
                .toList();
    }

    @Override
    public ActivityTypeDto save(UUID institutionId, ActivityTypeCreationDto creationDto) {
        Institution institution = Optional.ofNullable(institutionRepository.findById(institutionId))
                .orElseThrow(() -> new InstitutionWithIdNotFoundException(institutionId));

        ActivityType activityType = ActivityTypeDtoMapper.convertDtoToEntity(creationDto);
        activityType.setInstitution(institution);

        activityTypeRepository.save(activityType);

        institution.getActivityTypes().add(activityType);

        return ActivityTypeDtoMapper.convertEntityToDto(activityType);
    }

    @Override
    public void deleteById(UUID institutionId, UUID id) {
        ActivityType activityType = Optional.ofNullable(activityTypeRepository.findById(id))
                .orElseThrow(() -> new ActivityTypeWithIdNotFoundException(id));

        if(!activityType.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        activityTypeRepository.deleteById(id);
    }
}
