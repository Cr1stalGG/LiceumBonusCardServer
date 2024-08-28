package by.grsu.liceum.controller;

import by.grsu.liceum.dto.group.AddMembersDto;
import by.grsu.liceum.dto.group.GroupCreationDto;
import by.grsu.liceum.dto.group.GroupFullDto;
import by.grsu.liceum.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupController { //todo check
    private final GroupService groupService;

    @GetMapping("/{id}")
    public GroupFullDto findGroupById(@PathVariable("id") long id){
        return groupService.findGroupById(id);
    }

    @PostMapping
    public GroupFullDto createNewGroup(@RequestBody GroupCreationDto creationDto){
        return groupService.createNewGroup(creationDto);
    }

    @PostMapping("/add")
    void addMembersToTheGroup(@RequestBody AddMembersDto addMembersDto){
        groupService.addMembersToTheGroup(addMembersDto);
    }

    @DeleteMapping("/{id}")
    void deleteGroupById(@PathVariable("id") long id){
        groupService.deleteGroupById(id);
    }
}
