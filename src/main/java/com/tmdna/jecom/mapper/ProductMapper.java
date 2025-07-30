package com.tmdna.jecom.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tmdna.jecom.dto.ProductRequest;
import com.tmdna.jecom.dto.ProductResponse;
import com.tmdna.jecom.model.Product;

@Component
public class ProductMapper {

    @Value("${app.base-url}")
    private String baseUrl;

    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .brand(request.getBrand())
                .category(request.getCategory())
                .description(request.getDescription())
                .price(request.getPrice())
                .releaseDate(request.getReleaseDate())
                .isAvailable(request.isAvailable())
                .quantity(request.getQuantity())
                .build();
    }

    public ProductResponse toResponse(Product product) {
        String imageUrl = product.getImage() != null
                ? baseUrl + "/api/images/" + product.getImage().getId()
                : null;

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getCategory(),
                product.getDescription(),
                product.getPrice(),
                product.getReleaseDate(),
                product.isAvailable(),
                product.getQuantity(),
                imageUrl
        );
    }
}
