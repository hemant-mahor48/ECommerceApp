package com.ms.order_service.controller;

import com.ms.order_service.model.OrderLineResponse;
import com.ms.order_service.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService service;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderLineResponse>> findOrderLinesByOrderId(@PathVariable Long orderId){
        return ResponseEntity.ok(service.findAllOrderLines(orderId));
    }
}
