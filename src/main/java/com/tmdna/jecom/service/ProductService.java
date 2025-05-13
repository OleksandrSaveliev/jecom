package com.tmdna.jecom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tmdna.jecom.dto.ProductRequest;
import com.tmdna.jecom.dto.ProductResponse;
import com.tmdna.jecom.exception.ResourceNotFound;
import com.tmdna.jecom.mapper.ProductMapper;
import com.tmdna.jecom.model.Product;
import com.tmdna.jecom.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Product with Id: " + id + " not found"));
        return productMapper.toResponse(product);
    }

    public Product saveProduct(ProductRequest request) {
        var product = productMapper.toEntity(request);

        return productRepository.save(product);
    }

    public Product updateProduct(ProductRequest request, int id) {
        var product = productMapper.toEntity(request);
        product.setId(id);
        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
