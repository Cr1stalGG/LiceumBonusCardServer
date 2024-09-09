package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.response.ResponseFullDto;
import by.grsu.liceum.dto.response.ResponseShortcutDto;
import by.grsu.liceum.dto.response_status.ResponseStatusDto;
import by.grsu.liceum.entity.Response;
import by.grsu.liceum.entity.ResponseStatus;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class ResponseDtoMapper {
    public static ResponseShortcutDto convertEntityToShortcutDto(Response source){
        return Optional.ofNullable(source)
                .map(ResponseDtoMapper::buildShortcutDto)
                .orElse(null);
    }

    public static ResponseFullDto convertEntityToFullDto(Response source){
        return Optional.ofNullable(source)
                .map(ResponseDtoMapper::buildFullDto)
                .orElse(null);
    }

    private static ResponseShortcutDto buildShortcutDto(Response source) {
        return ResponseShortcutDto.builder()
                .uuid(source.getId())
                .message(source.getMessage())
                .build();
    }

    private static ResponseFullDto buildFullDto(Response source) {
        return ResponseFullDto.builder()
                .uuid(source.getId())
                .message(source.getMessage())
                .responseStatusDto(buildResponseStatus(source.getResponseStatus()))
                .timeOfResponse(source.getTimeOfResponse())
                .build();
    }

    private static ResponseStatusDto buildResponseStatus(ResponseStatus source) {
        return Optional.ofNullable(source)
                .map(ResponseStatusDtoMapper::convertEntityToDto)
                .orElse(null);
    }
}
