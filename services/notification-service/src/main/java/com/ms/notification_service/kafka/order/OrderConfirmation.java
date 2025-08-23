package com.ms.notification_service.kafka.order;

import com.ms.notification_service.model.Customer;
import com.ms.notification_service.model.PaymentMethod;
import com.ms.notification_service.model.Product;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
