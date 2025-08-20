package com.ms.order_service.model;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
