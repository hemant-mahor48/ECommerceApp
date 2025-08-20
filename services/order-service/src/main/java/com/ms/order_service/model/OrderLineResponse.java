package com.ms.order_service.model;

public record OrderLineResponse(
        Long id,
        Double quantity
) {
}
