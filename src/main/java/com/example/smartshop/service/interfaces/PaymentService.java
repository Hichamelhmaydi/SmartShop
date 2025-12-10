package com.example.smartshop.service.interfaces;

import com.example.smartshop.dto.request.PaymentRequestDTO;
import com.example.smartshop.dto.response.PaymentResponseDTO;
import com.example.smartshop.entity.Payment;
import com.example.smartshop.enums.PaymentStatus;

import java.util.List;

public interface PaymentService {

    PaymentResponseDTO addPayment(Long orderId, PaymentRequestDTO request);

    PaymentResponseDTO updatePaymentStatus(Long paymentId, PaymentStatus newStatus);

    List<PaymentResponseDTO> getOrderPayments(Long orderId);

    List<PaymentResponseDTO> getPendingPayments();

    PaymentResponseDTO getPaymentById(Long id);
}

