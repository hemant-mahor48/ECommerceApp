package com.ms.customer_service.model;


public record CustomerResponse(
        String id,
        String firstName,
        String lastname,
        String email,
        Address address
) {
}
