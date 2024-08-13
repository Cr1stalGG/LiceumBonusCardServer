package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.group.GroupShortcutDto;
import by.grsu.liceum.entity.Group;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GroupDtoMapper {
    public static GroupShortcutDto convertEntityToShortcutDto(Group source) {
        return GroupShortcutDto.builder()
                .uuid(source.getId())
                .name(source.getName())
                .build();
    }
}
