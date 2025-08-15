package com.ms.product_service.controller;

import com.ms.product_service.model.ProductPurchaseRequest;
import com.ms.product_service.model.ProductPurchaseResponse;
import com.ms.product_service.model.ProductRequest;
import com.ms.product_service.model.ProductResponse;
import com.ms.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody @Valid ProductRequest request){
        return ResponseEntity.ok(service.createProduct(request));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId){
        return ResponseEntity.ok(service.findById(productId));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> getProductsPurchased(@RequestBody @Valid List<ProductPurchaseRequest> request){
        return ResponseEntity.ok(service.getProductsPurchased(request));
    }
}
