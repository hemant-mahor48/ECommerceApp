package com.ms.order_service.service;

import com.ms.order_service.clients.CustomerClient;
import com.ms.order_service.clients.ProductClient;
import com.ms.order_service.exception.BusinessException;
import com.ms.order_service.kafka.OrderProducer;
import com.ms.order_service.kafka.model.OrderConfirmation;
import com.ms.order_service.mapper.OrderMapper;
import com.ms.order_service.model.*;
import com.ms.order_service.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer producer;

    public Long createOrder(@Valid OrderRequest request) {
        var customer = customerClient.getCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("cannot create order :: No customer exists for this customerId :" + request.customerId()));

        var purchaseProducts = productClient.purchaseProducts(request.products());

        var order = orderRepository.save(mapper.toOrder(request));

        for(PurchaseRequest purchaseRequest : request.products()){
            orderLineService.saveOrderLine(
              new OrderLineRequest(
                      null,
                      order.getId(),
                      purchaseRequest.productId(),
                      purchaseRequest.quantity()
              )
            );
        }

        // todo Start payment process

        producer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> finalAll() {
        return orderRepository.findAll()
                .stream().map(mapper::toOrderResponse).toList();
    }

    public OrderResponse findById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::toOrderResponse).orElseThrow(() -> new EntityNotFoundException("Order Not Found with this orderId: " + orderId));
    }
}
