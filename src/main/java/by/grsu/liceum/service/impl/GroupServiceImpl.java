package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.group.AddMembersDto;
import by.grsu.liceum.dto.group.GroupCreationDto;
import by.grsu.liceum.dto.group.GroupFullDto;
import by.grsu.liceum.dto.mapper.GroupDtoMapper;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Group;
import by.grsu.liceum.entity.Institution;
import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.exception.GroupWithIdNotFoundException;
import by.grsu.liceum.exception.InstitutionWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidPermissionsException;
import by.grsu.liceum.exception.NullableGroupCreationDtoException;
import by.grsu.liceum.repository.AccountRepository;
import by.grsu.liceum.repository.GroupRepository;
import by.grsu.liceum.service.GroupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final AccountRepository accountRepository;
    private final GroupRepository groupRepository;

    @Override
    public GroupFullDto findGroupById(UUID institutionId, UUID id) {
        Group group = Optional.ofNullable(groupRepository.findById(id))
                .orElseThrow(() -> new GroupWithIdNotFoundException(id));

        if(!group.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        return GroupDtoMapper.convertEntityToFullDto(group);
    }

    @Override
    @Transactional
    public GroupFullDto createNewGroup(UUID institutionId, GroupCreationDto creationDto) {
        if(creationDto == null)
            throw new NullableGroupCreationDtoException();

        Account admin = Optional.ofNullable(accountRepository.findById(creationDto.getAdminId()))
                .orElseThrow(() -> new AccountWithIdNotFoundException(creationDto.getAdminId()));

        Institution institution = Optional.of(admin.getInstitution())
                .orElseThrow(() -> new InstitutionWithIdNotFoundException(institutionId));

        if(!admin.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        Group group = Group.builder()
                .admin(admin)
                .name(creationDto.getName())
                .institution(institution)
                .build();

        groupRepository.save(group);

        admin.getOtherGroups().add(group);

        if(group.getMembers() == null)
            group.setMembers(new ArrayList<>());

        for(UUID memberId : creationDto.getMembersId()){
            Account member = Optional.ofNullable(accountRepository.findById(memberId))
                    .orElseThrow(() -> new AccountWithIdNotFoundException(memberId));

            group.getMembers().add(member);
            member.getOtherGroups().add(group);
        }

        institution.getGroups().add(group);

        return GroupDtoMapper.convertEntityToFullDto(group);
    }

    @Override
    @Transactional
    public GroupFullDto addMembersToTheGroup(UUID institutionId, AddMembersDto addMembersDto) {
        Group group = groupRepository.findById(addMembersDto.getGroupId());

        if(!group.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        if(!group.getAdmin().getId().equals(addMembersDto.getAdminId()))
            throw new InvalidPermissionsException();

        for(UUID memberId : addMembersDto.getMembersId()){
            Account member = Optional.ofNullable(accountRepository.findById(memberId))
                    .orElseThrow(() -> new AccountWithIdNotFoundException(memberId));

            if(group.getMembers().stream().anyMatch(x -> x.getId().equals(memberId)))
                continue;

            group.getMembers().add(member);
            member.getOtherGroups().add(group);
        }

        return GroupDtoMapper.convertEntityToFullDto(group);
    }

    @Override
    public void deleteGroupById(UUID institutionId, UUID id) {
        Group group = Optional.ofNullable(groupRepository.findById(id))
            .orElseThrow(() -> new GroupWithIdNotFoundException(id));

        if(!group.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        groupRepository.deleteById(id);
    }
}
