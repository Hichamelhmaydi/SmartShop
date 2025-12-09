package com.example.smartshop.dto.response;

import com.example.smartshop.enums.PaymentStatus;
import com.example.smartshop.enums.PaymentType;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentResponseDTO {
    private Long id;
    private Integer paymentNumber;
    private BigDecimal amount;
    private PaymentType paymentType;
    private PaymentStatus status;
    private LocalDate paymentDate;
    private LocalDate settlementDate;
    private String reference;
    private String bank;
    private LocalDate dueDate;
    private Long orderId;
}