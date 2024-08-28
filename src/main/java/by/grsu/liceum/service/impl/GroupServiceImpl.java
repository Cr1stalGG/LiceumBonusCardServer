package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.group.AddMembersDto;
import by.grsu.liceum.dto.group.GroupCreationDto;
import by.grsu.liceum.dto.group.GroupFullDto;
import by.grsu.liceum.dto.mapper.GroupDtoMapper;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Group;
import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.exception.GroupWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidPermissionsException;
import by.grsu.liceum.exception.NullableGroupCreationDtoException;
import by.grsu.liceum.repository.AccountRepository;
import by.grsu.liceum.repository.GroupRepository;
import by.grsu.liceum.service.GroupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final AccountRepository accountRepository;
    private final GroupRepository groupRepository;

    @Override
    public GroupFullDto findGroupById(long id) {
        Group group = Optional.ofNullable(groupRepository.findById(id))
                .orElseThrow(() -> new GroupWithIdNotFoundException(id));

        return GroupDtoMapper.convertEntityToFullDto(group);
    }

    @Override
    @Transactional
    public GroupFullDto createNewGroup(GroupCreationDto creationDto) {
        if(creationDto == null)
            throw new NullableGroupCreationDtoException();

        Account admin = Optional.ofNullable(accountRepository.findById(creationDto.getAdminId()))
                .orElseThrow(() -> new AccountWithIdNotFoundException(creationDto.getAdminId()));

        Group group = Group.builder()
                .admin(admin)
                .name(creationDto.getName())
                .build();

        groupRepository.save(group);

        admin.getOtherGroups().add(group);

        for(Long memberId : creationDto.getMembersId()){
            Account member = accountRepository.findById(memberId)
                    .orElseThrow(() -> new AccountWithIdNotFoundException(memberId));

            group.getMembers().add(member);
            member.getOtherGroups().add(group);
        }

        return GroupDtoMapper.convertEntityToFullDto(group);
    }

    @Override
    @Transactional
    public void addMembersToTheGroup(AddMembersDto addMembersDto) {
        Group group = groupRepository.findById(addMembersDto.getGroupId());

        if(group.getAdmin().getId() != addMembersDto.getAdminId())
            throw new InvalidPermissionsException();

        for(Long memberId : addMembersDto.getMembersId()){
            Account member = accountRepository.findById(memberId)
                    .orElseThrow(() -> new AccountWithIdNotFoundException(memberId));

            group.getMembers().add(member);
            member.getOtherGroups().add(group);
        }
    }

    @Override
    public void deleteGroupById(long id) {
        findGroupById(id);

        groupRepository.deleteById(id);
    }
}
