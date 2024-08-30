package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.activity.ActivityCreationDto;
import by.grsu.liceum.dto.activity.ActivityFullDto;
import by.grsu.liceum.dto.activity.ActivityShortcutDto;
import by.grsu.liceum.dto.mapper.ActivityDtoMapper;
import by.grsu.liceum.entity.Activity;
import by.grsu.liceum.entity.ActivityType;
import by.grsu.liceum.exception.ActivityTypeWithIdNotFoundException;
import by.grsu.liceum.exception.ActivityWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidPermissionsException;
import by.grsu.liceum.repository.ActivityRepository;
import by.grsu.liceum.repository.ActivityTypeRepository;
import by.grsu.liceum.service.ActivityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final ActivityTypeRepository activityTypeRepository;

    @Override
    public List<ActivityShortcutDto> findAll(UUID institutionId) {
        return activityRepository.findAllByActivityType_Institution_Id(institutionId).stream()
                .map(ActivityDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    public ActivityFullDto findById(UUID institutionId, UUID id) {
        Activity activity = Optional.ofNullable(activityRepository.findById(id))
                .orElseThrow(() -> new ActivityWithIdNotFoundException(id));

        if(activity.getActivityType().getInstitution().getId() != institutionId)
            throw new InvalidPermissionsException();

        return ActivityDtoMapper.convertEntityToFullDto(activity);
    }

    @Override
    @Transactional
    public ActivityFullDto createActivity(UUID institutionId, ActivityCreationDto creationDto) {
        Activity activity = ActivityDtoMapper.convertDtoToEntity(creationDto);

        ActivityType activityType = Optional.ofNullable(activityTypeRepository.findById(creationDto.getActivityTypeId()))
                .orElseThrow(() -> new ActivityTypeWithIdNotFoundException(creationDto.getActivityTypeId()));

        activityType.getActivities().add(activity);

        activity.setActivityType(activityType);
        activityRepository.save(activity);

        return ActivityDtoMapper.convertEntityToFullDto(activity);
    }

    @Override
    public void deleteById(UUID institutionId, UUID id) {
        Activity activity = Optional.ofNullable(activityRepository.findById(id))
                .orElseThrow(() -> new ActivityWithIdNotFoundException(id));

        if (activity.getActivityType().getInstitution().getId() != institutionId)
            throw new InvalidPermissionsException();

        activityRepository.deleteById(id);
    }
}
