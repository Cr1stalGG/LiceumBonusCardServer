package by.grsu.liceum.controller;

import by.grsu.liceum.dto.auth.AuthRequest;
import by.grsu.liceum.dto.auth.AuthResponse;
import by.grsu.liceum.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public AuthResponse authenticate(@RequestBody @Valid AuthRequest request){
        return authenticationService.authenticate(request);
    }
}
