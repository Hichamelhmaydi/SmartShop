package com.example.smartshop.service.interfaces;

import com.example.smartshop.dto.request.PaymentRequestDTO;
import com.example.smartshop.dto.response.PaymentResponseDTO;
import com.example.smartshop.entity.Payment;
import java.util.List;

public interface IPaymentService {

    PaymentResponseDTO createPayment(PaymentRequestDTO request);

    List<PaymentResponseDTO> getPaymentsByOrder(Long orderId);

    PaymentResponseDTO updatePaymentStatus(Long paymentId, String status);

    PaymentResponseDTO settlePayment(Long paymentId);

    PaymentResponseDTO rejectPayment(Long paymentId);
}
