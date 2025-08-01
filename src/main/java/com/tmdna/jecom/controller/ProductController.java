package com.tmdna.jecom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmdna.jecom.dto.product.ProductRequest;
import com.tmdna.jecom.dto.product.ProductResponse;
import com.tmdna.jecom.entity.Product;
import com.tmdna.jecom.service.ProductService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody @Valid ProductRequest request) {

        Product product = productService.saveProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", product.getId()));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("id", id));
    }
}
