package com.tmdna.jecom.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tmdna.jecom.service.ProductImageService;

@RestController
@RequestMapping("/api/images")
public class ProductImageController {

    private final ProductImageService imageService;

    public ProductImageController(ProductImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam("productId") int productId) throws IOException {

        imageService.saveImageForProduct(file, productId);
        return ResponseEntity.ok("Image uploaded successfully: " + file.getOriginalFilename());
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(imageService.getContentType(id)))
                .body(imageService.getImageData(id));
    }
}
