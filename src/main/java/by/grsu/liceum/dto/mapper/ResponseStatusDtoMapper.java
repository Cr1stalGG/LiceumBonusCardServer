package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.response_status.ResponseStatusDto;
import by.grsu.liceum.entity.ResponseStatus;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class ResponseStatusDtoMapper {
    public static ResponseStatusDto convertEntityToDto(ResponseStatus source){
        return Optional.ofNullable(source)
                .map(ResponseStatusDtoMapper::buildDto)
                .orElse(null);
    }

    private static ResponseStatusDto buildDto(ResponseStatus source) {
        return ResponseStatusDto.builder()
                .uuid(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }
}
