package com.ms.order_service.clients;

import com.ms.order_service.model.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "customer-service", url = "http://localhost:8222")
public interface CustomerClient {

    @GetMapping("/api/v1/customers/{customerId}")
    Optional<CustomerResponse> getCustomerById(@PathVariable String customerId);
}
