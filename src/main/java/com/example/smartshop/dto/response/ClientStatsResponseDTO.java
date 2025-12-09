package com.example.smartshop.dto.response;

import lombok.Data;

@Data
public class ClientStatsResponseDTO {
    private Long clientId;
    private String clientName;
    private Integer totalOrders;
    private Double totalSpent;
    private String firstOrderDate;
    private String lastOrderDate;
}