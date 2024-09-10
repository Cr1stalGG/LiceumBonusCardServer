package by.grsu.liceum.dto.institution;

import by.grsu.liceum.dto.image.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionShortcutDto implements Serializable {
    private UUID uuid;
    private String name;
    private String city;
    private ImageDto imageDto;
}
