package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.activity_type.ActivityTypeCreationDto;
import by.grsu.liceum.dto.activity_type.ActivityTypeDto;
import by.grsu.liceum.dto.mapper.ActivityTypeDtoMapper;
import by.grsu.liceum.entity.ActivityType;
import by.grsu.liceum.exception.ActivityTypeWithIdNotFoundException;
import by.grsu.liceum.repository.ActivityTypeRepository;
import by.grsu.liceum.service.ActivityTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityTypeServiceImpl implements ActivityTypeService {
    private final ActivityTypeRepository activityTypeRepository;

    @Override
    public ActivityTypeDto findById(long id) {
        ActivityType activityType = Optional.ofNullable(activityTypeRepository.findById(id))
                .orElseThrow(() -> new ActivityTypeWithIdNotFoundException(id));

        return ActivityTypeDtoMapper.convertEntityToDto(activityType);
    }

    @Override
    public List<ActivityTypeDto> findAll() {
        return activityTypeRepository.findAll().stream()
                .map(ActivityTypeDtoMapper::convertEntityToDto)
                .toList();
    }

    @Override
    public ActivityTypeDto save(ActivityTypeCreationDto creationDto) {
        ActivityType activityType = ActivityTypeDtoMapper.convertDtoToEntity(creationDto);

        activityTypeRepository.save(activityType);

        return ActivityTypeDtoMapper.convertEntityToDto(activityType);
    }

    @Override
    public void deleteById(long id) {
        findById(id);

        activityTypeRepository.deleteById(id);
    }
}
