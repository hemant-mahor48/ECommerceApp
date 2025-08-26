package com.ms.auth_service.service;

import com.ms.auth_service.mapper.UserMapper;
import com.ms.auth_service.model.AuthResponse;
import com.ms.auth_service.model.LoginRequest;
import com.ms.auth_service.model.UserRequest;
import com.ms.auth_service.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public Long createUser(@Valid UserRequest request) {
        if (repository.existsByUsername(request.username())) {
            throw new RuntimeException("Username already exists");
        }
        if (repository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }
        var user = mapper.toUser(request);
        return repository.save(user).getId();
    }

    public AuthResponse authenticateUser(@Valid LoginRequest request) {
        try{
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            String token = jwtService.generateToken(request.username());
            return AuthResponse.builder()
                    .username(request.username())
                    .token(token)
                    .type("Bearer")
                    .message("Authentication Successful")
                    .build();
        } catch (AuthenticationException e){
            throw new RuntimeException("Invalid username or password");
        }
    }
}
