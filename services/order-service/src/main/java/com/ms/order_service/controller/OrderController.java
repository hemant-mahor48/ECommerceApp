package com.ms.order_service.controller;

import com.ms.order_service.model.OrderRequest;
import com.ms.order_service.model.OrderResponse;
import com.ms.order_service.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody @Valid OrderRequest request, HttpServletRequest httpRequest){
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader == null) {
            throw new RuntimeException("Authorization header required");
        }
        return ResponseEntity.ok(service.createOrder(request, authHeader));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders(){
        return ResponseEntity.ok(service.finalAll());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId){
        return ResponseEntity.ok(service.findById(orderId));
    }
}
