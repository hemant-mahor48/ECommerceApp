package com.ms.auth_service.service;

import com.ms.auth_service.mapper.UserMapper;
import com.ms.auth_service.model.UserRequest;
import com.ms.auth_service.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public Long createUser(@Valid UserRequest request) {
        var user = mapper.toUser(request);
        return repository.save(user).getId();
    }
}
