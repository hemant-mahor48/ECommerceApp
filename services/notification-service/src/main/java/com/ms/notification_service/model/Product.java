package com.ms.notification_service.model;

import java.math.BigDecimal;

public record Product(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        Double quantity
) {
}
