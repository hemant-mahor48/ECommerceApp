package com.ms.order_service.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Long Id,
        String reference,

        @Positive(message = "order amount should be positive")
        BigDecimal amount,

        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,

        @NotNull(message = "CustomerId should not be null, blank or empty")
        @NotBlank(message = "CustomerId should not be null, blank or empty")
        @NotEmpty(message = "CustomerId should not be null, blank or empty")
        String customerId,

        @NotEmpty(message = "should purchase at least one product")
        List<PurchaseRequest> products
) {
}
