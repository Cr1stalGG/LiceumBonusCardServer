package by.grsu.liceum.service.impl;

import by.grsu.liceum.config.security.AccountUserDetailsConfiguration;
import by.grsu.liceum.dto.auth.AuthRequest;
import by.grsu.liceum.dto.auth.AuthResponse;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Role;
import by.grsu.liceum.repository.AccountRepository;
import by.grsu.liceum.security.jwt.JwtService;
import by.grsu.liceum.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final JwtService jwtService;

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));

        Account account = Optional.ofNullable(accountRepository.findByLogin(request.getLogin()))
                .orElseThrow();

        String jwtToken = jwtService.generateToken(new AccountUserDetailsConfiguration(account));

        return AuthResponse.builder()
                .token(jwtToken)
                .roles(account.getRoles().stream()
                        .map(Role::getName)
                        .toList())
                .build();
    }
}
