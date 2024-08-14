package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.auth.AuthRequest;
import by.grsu.liceum.dto.auth.AuthResponse;
import by.grsu.liceum.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        return null;
    }
}
