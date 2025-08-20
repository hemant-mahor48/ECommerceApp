package com.ms.customer_service.controller;

import com.ms.customer_service.model.CustomerRequest;
import com.ms.customer_service.model.CustomerResponse;
import com.ms.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){
        return ResponseEntity.ok(service.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request){
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
        return ResponseEntity.ok(service.findAllCustomers());
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String customer_id){
        return ResponseEntity.ok(service.findCustomerById(customer_id));
    }

    @GetMapping("/exists/{customer_id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String customer_id){
        return ResponseEntity.ok(service.existsByCustomerId(customer_id));
    }

    @DeleteMapping("/{customer_id}")
    public ResponseEntity<Void> deletedCustomerById(@PathVariable String customer_id){
        service.deleteById(customer_id);
        return ResponseEntity.accepted().build();
    }
}
