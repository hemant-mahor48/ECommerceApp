package com.ms.product_service.mapper;

import com.ms.product_service.model.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(@Valid ProductRequest request) {
        if(request == null)
            return null;
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQty(request.availableQty())
                .price(request.price())
                .category(Category.builder().id(request.categoryId()).build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQty(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, @NotNull(message = "quantity should not be null") Double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
