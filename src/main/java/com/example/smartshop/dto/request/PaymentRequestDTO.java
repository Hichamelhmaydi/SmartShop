package com.example.smartshop.dto.request;

import com.example.smartshop.enums.PaymentType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentRequestDTO {
    @NotNull(message = "Order ID is required")
    private Long orderId;
    
    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be positive")
    private BigDecimal amount;
    
    @NotNull(message = "Payment type is required")
    private PaymentType paymentType;
    
    private String reference;
    private String bank;
    private LocalDate dueDate;
}