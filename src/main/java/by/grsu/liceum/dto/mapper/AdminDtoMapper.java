package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.account.admin.AdminFullDto;
import by.grsu.liceum.dto.account.admin.AdminShortcutDto;
import by.grsu.liceum.dto.institution.InstitutionShortcutDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Institution;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class AdminDtoMapper {
    public static AdminFullDto convertEntityToFullDto(Account source){
        return Optional.ofNullable(source)
                .map(AdminDtoMapper::buildFullDto)
                .orElse(null);
    }

    public static AdminShortcutDto convertEntityToShortcutDto(Account source){
        return Optional.ofNullable(source)
                .map(AdminDtoMapper::buildShortcutDto)
                .orElse(null);
    }

    private static AdminShortcutDto buildShortcutDto(Account source) {
        return AdminShortcutDto.builder()
                .uuid(source.getId())
                .login(source.getLogin())
                .institution(buildInstitution(source.getInstitution()))
                .build();
    }

    private static AdminFullDto buildFullDto(Account source) {
        return AdminFullDto.builder()
                .uuid(source.getId())
                .login(source.getLogin())
                .password(source.getPassword())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .fatherName(source.getFatherName())
                .phoneNumber(source.getPhoneNumber())
                .institution(buildInstitution(source.getInstitution()))
                .build();
    }

    private static InstitutionShortcutDto buildInstitution(Institution source) {
        return Optional.ofNullable(source)
                .map(InstitutionDtoMapper::convertEntityToShortcutDto)
                .orElse(null);
    }
}
