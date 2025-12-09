package com.example.smartshop.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDTO {
    @NotNull(message = "Client ID is required")
    private Long clientId;
    
    @NotEmpty(message = "Order items cannot be empty")
    private List<OrderItemRequestDTO> items;
    
    private String promoCode;
}