package com.ms.auth_service.mapper;

import com.ms.auth_service.model.User;
import com.ms.auth_service.model.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User toUser(@Valid UserRequest request) {
        return User.builder()
                .id(request.id())
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
    }
}
