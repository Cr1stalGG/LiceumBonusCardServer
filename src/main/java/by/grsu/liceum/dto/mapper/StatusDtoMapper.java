package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.status.StatusDto;
import by.grsu.liceum.entity.TransactionStatus;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class StatusDtoMapper {
    public static StatusDto convertEntityToDto(TransactionStatus source) {
        return Optional.ofNullable(source)
                .map(StatusDtoMapper::buildStatus)
                .orElse(null);
    }

    private static StatusDto buildStatus(TransactionStatus source) {
        return StatusDto.builder()
                .uuid(source.getId())
                .name(source.getName().name())
                .description(source.getDescription())
                .build();
    }
}
