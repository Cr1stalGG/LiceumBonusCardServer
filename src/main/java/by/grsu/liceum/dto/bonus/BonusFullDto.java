package by.grsu.liceum.dto.bonus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BonusFullDto {
    private UUID uuid;
    private String name;
    private String description;
    private int price;
    private int count;
    private Date timeOfEnd;
}
