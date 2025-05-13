package com.tmdna.jecom.dto;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String brand;
    @NotBlank
    private String category;
    @NotBlank
    private String description;
    @DecimalMin("0.0")
    private BigDecimal price;
    @NotBlank
    private Date releaseDate;
    private boolean isAvailable;
    @Min(0)
    private int quantity;
}
