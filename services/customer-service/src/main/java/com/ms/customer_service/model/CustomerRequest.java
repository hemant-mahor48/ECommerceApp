package com.ms.customer_service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,

        @NotNull(message = "FirstName shouldn't be null")
        String firstName,

        @NotNull(message = "LastName shouldn't be null")
        String lastName,

        @NotNull(message = "Email shouldn't be null")
        @Email(message = "This is not a valid Email")
        String email,
        Address address
) {
}
