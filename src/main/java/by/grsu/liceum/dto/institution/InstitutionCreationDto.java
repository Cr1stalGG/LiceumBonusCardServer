package by.grsu.liceum.dto.institution;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionCreationDto {
    @NotBlank
    private String name;
    @NotBlank
    @Size(max = 60)
    private String city;
}
