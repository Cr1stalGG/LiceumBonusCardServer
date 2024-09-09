package by.grsu.liceum.dto.account;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(max = 30)
    private String firstName;
    @NotBlank
    @Size(max = 30)
    private String lastName;
    @NotBlank
    @Size(max = 30)
    private String fatherName;
    @NotBlank
    @Size(max = 20)
    private String phoneNumber;
    @Min(1)
    @NotNull
    private int grade;
    @NotNull
    private List<@NotBlank String> roleNames; //todo optional change to number(1 - user, 2 - admin...)
}
