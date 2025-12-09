package com.example.smartshop.dto.response;

import com.example.smartshop.enums.CustomerTier;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ClientResponseDTO {
    private Long id;
    private String username;
    private String email;
    private CustomerTier tier;
    private Integer totalOrders;
    private Double totalSpent;
    private LocalDateTime firstOrderDate;
    private LocalDateTime lastOrderDate;
}