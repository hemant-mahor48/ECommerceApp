package com.ms.product_service.service;

import com.ms.product_service.exception.ProductPurchaseException;
import com.ms.product_service.mapper.ProductMapper;
import com.ms.product_service.model.ProductPurchaseRequest;
import com.ms.product_service.model.ProductPurchaseResponse;
import com.ms.product_service.model.ProductRequest;
import com.ms.product_service.model.ProductResponse;
import com.ms.product_service.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Long createProduct(@Valid ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse findById(Long productId) {
        return repository.findById(productId).
                map(mapper::toProductResponse)
                .orElseThrow(()-> new EntityNotFoundException("Product Not Found for this productId: " + productId));
    }

    public List<ProductPurchaseResponse> getProductsPurchased(@Valid List<ProductPurchaseRequest> request) {
        var productIds = request.stream().map(ProductPurchaseRequest::productId).toList();
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()){
            throw new ProductPurchaseException("One or more products are not found");
        }

        var productsFromRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productFromRequest = productsFromRequest.get(i);
            if(product.getAvailableQty() < productFromRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for the product with productId: " + productFromRequest.productId());
            }
            var leftQuantity = product.getAvailableQty() - productFromRequest.quantity();
            product.setAvailableQty(leftQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productFromRequest.quantity()));
        }
        return purchasedProducts;
    }
}
