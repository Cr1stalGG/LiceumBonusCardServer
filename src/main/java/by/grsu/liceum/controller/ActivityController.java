package by.grsu.liceum.controller;

import by.grsu.liceum.dto.activity.ActivityCreationDto;
import by.grsu.liceum.dto.activity.ActivityFullDto;
import by.grsu.liceum.dto.activity.ActivityShortcutDto;
import by.grsu.liceum.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/institutions/{institutionId}/activities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    @GetMapping
    public List<ActivityShortcutDto> findAll(@PathVariable("institutionId") long institutionId){
        return activityService.findAll(institutionId);
    }

    @GetMapping("/{id}")
    public ActivityFullDto findById(@PathVariable("institutionId") long institutionId, @PathVariable("id") long id){
        return activityService.findById(institutionId, id);
    }

    @PostMapping
    @Secured(value = "ROLE_ADMIN")
    public ActivityFullDto createActivity(@PathVariable("institutionId") long institutionid, @RequestBody ActivityCreationDto creationDto){
        return activityService.createActivity(institutionid, creationDto);
    }

    @DeleteMapping("/{id}")
    @Secured(value = "ROLE_ADMIN")
    public void deleteById(@PathVariable("institutionId") long institutionId, @PathVariable("/{id}") long id){
        activityService.deleteById(institutionId, id);
    }
}
