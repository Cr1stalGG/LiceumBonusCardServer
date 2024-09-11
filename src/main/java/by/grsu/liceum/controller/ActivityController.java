package by.grsu.liceum.controller;

import by.grsu.liceum.dto.activity.ActivityCreationDto;
import by.grsu.liceum.dto.activity.ActivityFullDto;
import by.grsu.liceum.dto.activity.ActivityShortcutDto;
import by.grsu.liceum.service.ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/institutions/{institutionId}/activities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    @GetMapping
    @Cacheable(value = "activities", key = "#institutionId")
    @PreAuthorize("isAuthenticated()")
    public List<ActivityShortcutDto> findAll(@PathVariable("institutionId") UUID institutionId){
        return activityService.findAll(institutionId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ActivityFullDto findById(@PathVariable("institutionId") UUID institutionId, @PathVariable("id") UUID id){
        return activityService.findById(institutionId, id);
    }

    @PostMapping
    @CacheEvict(value = "activities", key = "#institutionId", allEntries = true)
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER')")
    public ActivityFullDto createActivity(@PathVariable("institutionId") UUID institutionId, @RequestBody @Valid ActivityCreationDto creationDto){
        return activityService.createActivity(institutionId, creationDto);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "activities", key = "#institutionId", allEntries = true)
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public void deleteById(@PathVariable("institutionId") UUID institutionId, @PathVariable("id") UUID id){
        activityService.deleteById(institutionId, id);
    }
}
