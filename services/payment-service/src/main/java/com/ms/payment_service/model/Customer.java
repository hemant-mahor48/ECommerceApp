package com.ms.payment_service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,

        @NotNull(message = "FirstName shouldn't be null")
        String firstName,

        @NotNull(message = "LastName shouldn't be null")
        String lastName,

        @NotNull(message = "Email shouldn't be null")
        @Email(message = "This is not a valid Email")
        String email
) {
}
