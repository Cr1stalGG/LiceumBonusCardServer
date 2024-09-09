package by.grsu.liceum.dto.account.admin;

import by.grsu.liceum.dto.image.ImageDto;
import by.grsu.liceum.dto.institution.InstitutionShortcutDto;
import by.grsu.liceum.dto.response.ResponseShortcutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminFullDto {
    private UUID uuid;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phoneNumber;
    private InstitutionShortcutDto institution;
    private ImageDto image;//todo check another info
    private List<ResponseShortcutDto> responses;
}
