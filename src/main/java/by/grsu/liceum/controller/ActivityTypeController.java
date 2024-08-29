package by.grsu.liceum.controller;

import by.grsu.liceum.dto.activity_type.ActivityTypeCreationDto;
import by.grsu.liceum.dto.activity_type.ActivityTypeDto;
import by.grsu.liceum.service.ActivityTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/institutions/{institutionId}/activities/types")
@RequiredArgsConstructor
public class ActivityTypeController {
    private final ActivityTypeService activityTypeService;

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ActivityTypeDto findById(@PathVariable("institutionId") long institutionId, @PathVariable("id") long id){
        return activityTypeService.findById(institutionId, id);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<ActivityTypeDto> findAll(@PathVariable("institutionId") long institutionId){
        return activityTypeService.findAll(institutionId);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ActivityTypeDto createActivityType(@PathVariable("institutionId") long institutionId, @RequestBody ActivityTypeCreationDto creationDto){
        return activityTypeService.save(institutionId, creationDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public void deleteById(@PathVariable("institutionId") long institutionId, @PathVariable("id") long id){
        activityTypeService.deleteById(institutionId, id);
    }
}
