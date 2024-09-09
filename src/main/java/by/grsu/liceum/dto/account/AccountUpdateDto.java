package by.grsu.liceum.dto.account;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
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
public class AccountUpdateDto {
    @Size(max = 30)
    private String firstName;
    @Size(max = 30)
    private String lastName;
    @Size(max = 30)
    private String fatherName;
    @Size(max = 20)
    private String phoneNumber;
    @Min(1)
    private int grade;
}
