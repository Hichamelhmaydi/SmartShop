package com.example.smartshop.dto.response;

import com.example.smartshop.enums.OrderStatus;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderSummaryResponseDTO {
    private Long orderId;
    private LocalDateTime creationDate;
    private BigDecimal totalAmount;
    private OrderStatus status;
}