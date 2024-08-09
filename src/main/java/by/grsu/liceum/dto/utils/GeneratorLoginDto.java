package by.grsu.liceum.dto.utils;

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
public class GeneratorLoginDto {
    private String firstName;
    private String lastName;
    private String fatherName;
    private int yearOfStartOfStudies;
}
