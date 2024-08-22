package by.grsu.liceum.dto.auth;

import by.grsu.liceum.entity.enums.RoleConstant;
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
public class AuthResponse {
    private String token;
    private List<RoleConstant> roles; //todo optional change to number
}
