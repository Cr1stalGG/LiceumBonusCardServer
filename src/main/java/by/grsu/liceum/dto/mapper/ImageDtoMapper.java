package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.image.ImageCreationDto;
import by.grsu.liceum.dto.image.ImageDto;
import by.grsu.liceum.entity.Image;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class ImageDtoMapper {

    public static Image convertDtoToEntity(ImageCreationDto source) {
        return Optional.ofNullable(source)
                .map(ImageDtoMapper::buildEntity)
                .orElse(null);
    }

    public static ImageDto convertEntityToDto(Image source) {
        return Optional.ofNullable(source)
                .map(ImageDtoMapper::buildDto)
                .orElse(null);
    }

    private static ImageDto buildDto(Image source) {
        return ImageDto.builder()
                .id(source.getId())
                .objectName(source.getObjectName())
                .bucketName(source.getBucketName())
                .build();
    }

    private static Image buildEntity(ImageCreationDto source) {
        return Image.builder()
                .bucketName(source.getBucketName())
                .objectName(source.getObjectName())
                .build();
    }
}
