package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.group.GroupFullDto;
import by.grsu.liceum.dto.group.GroupShortcutDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Group;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class GroupDtoMapper {

    public static GroupShortcutDto convertEntityToShortcutDto(Group source){
        return Optional.ofNullable(source)
                .map(GroupDtoMapper::buildShortcutDto)
                .orElse(null);
    }

    public static GroupFullDto convertEntityToFullDto(Group source) {
        return Optional.ofNullable(source)
                .map(GroupDtoMapper::buildFullDto)
                .orElse(null);
    }

    private static GroupFullDto buildFullDto(Group source) {
        return GroupFullDto.builder()
                .uuid(source.getId())
                .name(source.getName())
                .admin(buildAdmin(source.getAdmin()))
                .members(buildMembers(source.getMembers())) //todo у всех школ будет группа 11а - поиск доп параметр
                .build();
    }

    private static AccountShortcutDto buildAdmin(Account account){
        return Optional.ofNullable(account)
                .map(AccountDtoMapper::convertEntityToShortcutDto)
                .orElse(null);
    }

    private static List<AccountShortcutDto> buildMembers(List<Account> source) {
        if(source == null)
            return new ArrayList<>();
        else
            return source.stream()
                    .map(AccountDtoMapper::convertEntityToShortcutDto)
                    .toList();
    }

    private static GroupShortcutDto buildShortcutDto(Group source) {
        return GroupShortcutDto.builder()
                .uuid(source.getId())
                .name(source.getName())
                .build();
    }
}
