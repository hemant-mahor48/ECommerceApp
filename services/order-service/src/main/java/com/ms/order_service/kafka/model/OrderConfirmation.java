package com.ms.order_service.kafka.model;

import com.ms.order_service.model.CustomerResponse;
import com.ms.order_service.model.PaymentMethod;
import com.ms.order_service.model.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
