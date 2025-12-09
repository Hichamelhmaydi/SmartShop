package com.example.smartshop.dto.response;

import com.example.smartshop.enums.OrderStatus;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {
    private Long id;
    private LocalDateTime creationDate;
    private BigDecimal subtotal;
    private BigDecimal discountAmount;
    private BigDecimal vatAmount;
    private BigDecimal totalAmount;
    private String promoCode;
    private OrderStatus status;
    private BigDecimal remainingAmount;
    private Long clientId;
    private String clientName;
    private List<OrderItemResponseDTO> items;
    private List<PaymentResponseDTO> payments;
}