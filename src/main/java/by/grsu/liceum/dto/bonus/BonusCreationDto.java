package by.grsu.liceum.dto.bonus;

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
    private String name;
    private String description;
    private int price;
    private int count;
    private Date timeOfEnd;
}
