package com.ms.order_service.mapper;

import com.ms.order_service.model.Order;
import com.ms.order_service.model.OrderLine;
import com.ms.order_service.model.OrderLineRequest;
import com.ms.order_service.model.OrderLineResponse;

public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .productId(orderLineRequest.productId())
                .quantity(orderLineRequest.quantity())
                .order(Order.builder()
                        .id(orderLineRequest.id()).build())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
