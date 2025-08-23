package com.ms.payment_service.service;

import com.ms.payment_service.kafka.NotificationProducer;
import com.ms.payment_service.kafka.model.PaymentNotificationRequest;
import com.ms.payment_service.mapper.PaymentMapper;
import com.ms.payment_service.model.PaymentRequest;
import com.ms.payment_service.repository.PaymentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer producer;

    public Long createPayment(@Valid PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));

        producer.sentNotification(new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstName(),
                request.customer().lastName(),
                request.customer().email()
        ));

        return payment.getId();
    }
}
