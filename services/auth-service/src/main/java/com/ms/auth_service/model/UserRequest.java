package com.ms.auth_service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotNull(message = "Id should not be null")
        Long id,
        @NotNull(message = "Please provide the username")
        String username,
        @Email(message = "Please provide the valid email")
        String email,
        @NotNull(message = "Please provide the password")
        String password
) {
}
