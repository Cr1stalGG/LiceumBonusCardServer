package by.grsu.liceum.service;


import by.grsu.liceum.dto.group.AddMembersDto;
import by.grsu.liceum.dto.group.GroupCreationDto;
import by.grsu.liceum.dto.group.GroupFullDto;

import java.util.UUID;

public interface GroupService {
    GroupFullDto findGroupById(UUID institutionId, UUID id);
    GroupFullDto createNewGroup(UUID institutionId, GroupCreationDto creationDto);
    GroupFullDto addMembersToTheGroup(UUID institutionId, AddMembersDto addMembersDto);
    void deleteGroupById(UUID institutionId, UUID id);
}
