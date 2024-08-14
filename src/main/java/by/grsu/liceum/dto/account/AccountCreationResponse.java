package by.grsu.liceum.dto.account;

import by.grsu.liceum.dto.card.CardDto;
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
public class AccountCreationResponse {
    private long uuid;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phoneNumber;
    private CardDto card;
}