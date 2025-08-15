package com.ms.product_service.model;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Double availableQty,
        BigDecimal price,
        Long categoryId,
        String categoryName,
        String categoryDescription
) {
}
