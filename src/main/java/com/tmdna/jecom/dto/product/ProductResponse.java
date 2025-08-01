package com.tmdna.jecom.dto.product;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponse {

    private int id;
    private String name;
    private String brand;
    private String category;
    private String description;
    private BigDecimal price;
    private Date releaseDate;
    private boolean isAvailable;
    private int quantity;
    private String imageUrl;
}
