package com.ms.order_service.service;

import com.ms.order_service.mapper.OrderLineMapper;
import com.ms.order_service.model.OrderLineRequest;
import com.ms.order_service.model.OrderLineResponse;
import com.ms.order_service.repository.OrderLineRepository;
import com.ms.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;
    private final OrderRepository orderRepository;

    public Long saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = orderRepository.findById(orderLineRequest.orderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        var orderLine = orderLineMapper.toOrderLine(orderLineRequest);
        orderLine.setOrder(order);
        return orderLineRepository.save(orderLine).getId();
    }

    public List<OrderLineResponse> findAllOrderLines(Long orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream().map(orderLineMapper::toOrderLineResponse).collect(Collectors.toList());
    }
}
