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
public class AdminShortcutDto {
    private long uuid;
    private String login;
    private InstitutionShortcutDto institution;
}
