package com.ms.order_service.model;

import jakarta.validation.constraints.NotNull;

public record OrderLineRequest(
        Long id,
        Long orderId,
        Long productId,
        Double quantity) {
}
