package com.ms.customer_service.mapper;

import com.ms.customer_service.model.Customer;
import com.ms.customer_service.model.CustomerRequest;
import com.ms.customer_service.model.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(@Valid CustomerRequest request) {
        if(request == null){
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastname(request.lastName())
                .email(request.email())
                .address(request.address()).build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastname(), customer.getEmail(), customer.getAddress());
    }
}
