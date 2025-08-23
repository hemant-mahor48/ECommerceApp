package com.ms.order_service.mapper;

import com.ms.order_service.model.Order;
import com.ms.order_service.model.OrderRequest;
import com.ms.order_service.model.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toOrder(@Valid OrderRequest request) {
        return Order.builder()
                .id(request.Id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
