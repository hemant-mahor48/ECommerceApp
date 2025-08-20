package com.ms.order_service.model;

import jakarta.validation.constraints.NotNull;

public record PurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Long productId,

        @NotNull(message = "quantity is mandatory")
        Double quantity
) {
}
