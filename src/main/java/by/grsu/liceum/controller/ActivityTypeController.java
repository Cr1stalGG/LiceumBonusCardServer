package by.grsu.liceum.controller;

import by.grsu.liceum.dto.activity_type.ActivityTypeCreationDto;
import by.grsu.liceum.dto.activity_type.ActivityTypeDto;
import by.grsu.liceum.service.ActivityTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activities/types")
@RequiredArgsConstructor
public class ActivityTypeController {
    private final ActivityTypeService activityTypeService;

    @GetMapping("/{id}")
    public ActivityTypeDto findById(@PathVariable("id") long id){
        return activityTypeService.findById(id);
    }

    @GetMapping
    public List<ActivityTypeDto> findAll(){
        return activityTypeService.findAll();
    }

    @PostMapping
    public ActivityTypeDto createActivityType(@RequestBody ActivityTypeCreationDto creationDto){
        return activityTypeService.save(creationDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        activityTypeService.deleteById(id);
    }
}
