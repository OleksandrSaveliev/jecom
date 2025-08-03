package com.tmdna.jecom.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tmdna.jecom.exception.ResourceNotFound;
import com.tmdna.jecom.entity.ProductImage;
import com.tmdna.jecom.repository.ProductImageRepository;
import com.tmdna.jecom.repository.ProductRepository;
import com.tmdna.jecom.utils.ImageUtility;

@Service
public class ProductImageService {

    private final ProductImageRepository imageRepository;
    private final ProductRepository productRepository;

    public ProductImageService(ProductImageRepository imageRepository, ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    public void saveImageForProduct(MultipartFile file, int productId) throws IOException {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFound("Product not found: " + productId));

        ProductImage image = ProductImage.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes()))
                .product(product)
                .build();

        image.setProduct(product);
        product.setImage(image);

        productRepository.save(product);
    }

    public byte[] getImageData(int id) {
        return imageRepository.findById(id)
                .map(image -> {
                    if (image.getImage() == null) {
                        return null;
                    }
                    return ImageUtility.decompressImage(image.getImage());
                })
                .orElse(null);
    }

    public String getContentType(int id) {
        return imageRepository.findById(id)
                .map(ProductImage::getType)
                .orElseThrow(() -> new ResourceNotFound("Image not found: " + id));
    }
}
