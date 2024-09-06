package by.grsu.liceum.dto.bonus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BonusCreationDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @Min(1)
    private int price;
    @Min(1)
    private int count;
    private Date timeOfEnd;
}
