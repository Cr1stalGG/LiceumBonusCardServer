package by.grsu.liceum.dto.account.admin;

import by.grsu.liceum.dto.institution.InstitutionShortcutDto;
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
public class AdminFullDto {
    private long uuid;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phoneNumber;
    private InstitutionShortcutDto institution;//todo check another info
}
