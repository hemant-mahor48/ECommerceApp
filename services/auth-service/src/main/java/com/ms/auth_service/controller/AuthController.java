package com.ms.auth_service.controller;

import com.ms.auth_service.model.AuthResponse;
import com.ms.auth_service.model.LoginRequest;
import com.ms.auth_service.model.UserRequest;
import com.ms.auth_service.service.AuthService;
import com.ms.auth_service.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserRequest request) {
        Long userId = authService.createUser(request);
        return ResponseEntity.ok("User registered successfully with ID: " + userId);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody @Valid LoginRequest request) {
        AuthResponse authResponse = authService.authenticateUser(request);
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/generateToken/{username}")
    public ResponseEntity<String> generateToken(@PathVariable String username) {
        return ResponseEntity.ok(jwtService.generateToken(username));
    }

    @GetMapping("/valid/{username}/{token}")
    public ResponseEntity<Boolean> isTokenValid(@PathVariable String username, @PathVariable String token) {
        return ResponseEntity.ok(jwtService.isTokenValid(token, username));
    }
}
