package com.ms.product_service.model;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "productId should not be null")
        Long productId,

        @NotNull(message = "quantity should not be null")
        Double quantity
) {
}
