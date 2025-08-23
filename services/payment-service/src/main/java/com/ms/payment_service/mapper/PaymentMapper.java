package com.ms.payment_service.mapper;

import com.ms.payment_service.model.Payment;
import com.ms.payment_service.model.PaymentRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment toPayment(@Valid PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();
    }
}
