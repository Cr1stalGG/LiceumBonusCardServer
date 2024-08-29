package by.grsu.liceum.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreationDto {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phoneNumber;
    private int yearOfStartOfStudies;
    private List<String> roleNames; //todo optional change to number(1 - user, 2 - admin...)
}
