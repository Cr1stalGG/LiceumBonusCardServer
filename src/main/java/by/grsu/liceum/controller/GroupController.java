package by.grsu.liceum.controller;

import by.grsu.liceum.dto.group.AddMembersDto;
import by.grsu.liceum.dto.group.GroupCreationDto;
import by.grsu.liceum.dto.group.GroupFullDto;
import by.grsu.liceum.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/institutions/{institutionId}/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public GroupFullDto findGroupById(@PathVariable("institutionId") UUID institutionId, @PathVariable("id") UUID id){
        return groupService.findGroupById(institutionId, id);
    }

    @PostMapping
    @CacheEvict(value = "groups", key = "#institutionId", allEntries = true)
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER', 'ROLE_TEACHER')")
    public GroupFullDto createNewGroup(@PathVariable("institutionId")UUID institutionId, @RequestBody @Valid GroupCreationDto creationDto){
        return groupService.createNewGroup(institutionId, creationDto);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER', 'ROLE_TEACHER')")
    GroupFullDto addMembersToTheGroup(@PathVariable("institutionId")UUID institutionId, @RequestBody @Valid AddMembersDto addMembersDto){
        return groupService.addMembersToTheGroup(institutionId, addMembersDto);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "groups", key = "#institutionId", allEntries = true)
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER', 'ROLE_TEACHER')")
    void deleteGroupById(@PathVariable("institutionId")UUID institutionId, @PathVariable("id") UUID id){
        groupService.deleteGroupById(institutionId, id);
    }
}
