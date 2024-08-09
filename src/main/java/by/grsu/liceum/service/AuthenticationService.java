package by.grsu.liceum.service;

import by.grsu.liceum.dto.auth.AuthRequest;
import by.grsu.liceum.dto.auth.AuthResponse;

public interface AuthenticationService {
    AuthResponse authenticate(AuthRequest request);
}
