package com.ms.product_service.repository;

import com.ms.product_service.model.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIdInOrderById(List<@NotNull(message = "productId should not be null") Long> productIds);
}
