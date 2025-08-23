package com.ms.notification_service.model;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
