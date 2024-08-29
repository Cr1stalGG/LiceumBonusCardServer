package by.grsu.liceum.service;


import by.grsu.liceum.dto.group.AddMembersDto;
import by.grsu.liceum.dto.group.GroupCreationDto;
import by.grsu.liceum.dto.group.GroupFullDto;

public interface GroupService {
    GroupFullDto findGroupById(long institutionId, long id);
    GroupFullDto createNewGroup(long institutionId, GroupCreationDto creationDto);
    void addMembersToTheGroup(long institutionId, AddMembersDto addMembersDto);
    void deleteGroupById(long institutionId, long id);
}
