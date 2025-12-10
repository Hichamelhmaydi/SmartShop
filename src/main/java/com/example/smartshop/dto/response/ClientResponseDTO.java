package com.example.smartshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.smartshop.enums.CustomerTier;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.smartshop.enums.CustomerTier;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;

    private CustomerTier tier;
    private Integer totalOrders;
    private BigDecimal totalSpent;
    private LocalDateTime firstOrderDate;
    private LocalDateTime lastOrderDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}