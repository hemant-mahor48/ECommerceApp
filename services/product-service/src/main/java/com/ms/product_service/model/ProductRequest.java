package com.ms.product_service.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        @NotNull(message = "Id must be provided")
        Long id,

        @NotNull(message = "Name must be provided")
        String name,

        @NotNull(message = "Description must be provided")
        String description,

        @Positive(message = "AvailableQty must be positive")
        Double availableQty,

        @Positive(message = "Price must be positive")
        BigDecimal price,

        @NotNull(message = "CategoryId must be provided")
        Long categoryId
) {
}
