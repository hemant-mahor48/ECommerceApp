package com.ms.customer_service.service;

import com.ms.customer_service.exception.CustomerNotFoundException;
import com.ms.customer_service.mapper.CustomerMapper;
import com.ms.customer_service.model.Customer;
import com.ms.customer_service.model.CustomerRequest;
import com.ms.customer_service.model.CustomerResponse;
import com.ms.customer_service.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(@Valid CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(()-> new CustomerNotFoundException(String.format("Customer Not Found for this id: " + request.id())));
        updateCustomerFields(customer, request);
        repository.save(customer);
    }

    private void updateCustomerFields(Customer customer, @Valid CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setFirstName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setFirstName(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll().stream().map(mapper::fromCustomer).collect(Collectors.toList());
    }

    public CustomerResponse findCustomerById(String customerId) {
        var customer = repository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException(String.format("Customer Not Found for this id: " + customerId)));
        return mapper.fromCustomer(customer);
    }

    public Boolean existsByCustomerId(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public void deleteById(String customerId) {
        repository.deleteById(customerId);
    }
}
