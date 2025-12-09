package com.example.smartshop.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductRequestDTO {
    @NotBlank(message = "Product name is required")
    private String name;
    
    @NotNull(message = "Unit price is required")
    @Min(value = 0, message = "Price must be positive")
    private BigDecimal unitPrice;
    
    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;
}