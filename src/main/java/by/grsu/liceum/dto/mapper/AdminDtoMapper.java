package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.account.admin.AdminFullDto;
import by.grsu.liceum.dto.account.admin.AdminShortcutDto;
import by.grsu.liceum.dto.image.ImageDto;
import by.grsu.liceum.dto.institution.InstitutionShortcutDto;
import by.grsu.liceum.dto.response.ResponseShortcutDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Image;
import by.grsu.liceum.entity.Institution;
import by.grsu.liceum.entity.Response;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
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
                .image(buildImage(source.getImage()))
                .build();
    }

    private static AdminFullDto buildFullDto(Account source) {
        return AdminFullDto.builder()
                .uuid(source.getId())
                .login(source.getLogin())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .fatherName(source.getFatherName())
                .phoneNumber(source.getPhoneNumber())
                .institution(buildInstitution(source.getInstitution()))
                .image(buildImage(source.getImage()))
                .responses(buildResponses(source.getResponses()))
                .build();
    }

    private static List<ResponseShortcutDto> buildResponses(List<Response> source){
        if(source == null)
            return new ArrayList<>();
        else
            return source.stream()
                    .map(ResponseDtoMapper::convertEntityToShortcutDto)
                    .toList();
    }

    private static InstitutionShortcutDto buildInstitution(Institution source) {
        return Optional.ofNullable(source)
                .map(InstitutionDtoMapper::convertEntityToShortcutDto)
                .orElse(null);
    }

    private static ImageDto buildImage(Image source) {
        return Optional.ofNullable(source)
                .map(ImageDtoMapper::convertEntityToDto)
                .orElse(null);
    }
}
