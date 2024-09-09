package by.grsu.liceum.dto.bonus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 40)
    private String name;
    @NotBlank
    private String description;
    @Min(1)
    @NotNull
    private int price;
    @Min(1)
    @NotNull
    private int count;
    @NotNull
    private Date timeOfEnd;
}
