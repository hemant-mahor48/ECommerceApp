package com.ms.order_service.service;

import com.ms.order_service.mapper.OrderLineMapper;
import com.ms.order_service.model.OrderLineRequest;
import com.ms.order_service.model.OrderLineResponse;
import com.ms.order_service.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private OrderLineRepository orderLineRepository;
    private OrderLineMapper orderLineMapper;

    public Long saveOrderLine(OrderLineRequest orderLineRequest) {
        var orderLine = orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(orderLine).getId();
    }

    public List<OrderLineResponse> findAllOrderLines(Long orderId) {
        return orderLineRepository.finaAllByOrderId(orderId)
                .stream().map(orderLineMapper::toOrderLineResponse).collect(Collectors.toList());
    }
}
