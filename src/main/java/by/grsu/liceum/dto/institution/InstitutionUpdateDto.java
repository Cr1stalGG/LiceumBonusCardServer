package by.grsu.liceum.dto.institution;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionUpdateDto {
    private String name;
    @Size(max = 60)
    private String city;
}
