package com.example.smartshop.service.impl;

import lombok.RequiredArgsConstructor;

import com.example.smartshop.dto.request.PaymentRequestDTO;
import com.example.smartshop.dto.response.PaymentResponseDTO;
import com.example.smartshop.entity.Order;
import com.example.smartshop.entity.Payment;
import com.example.smartshop.enums.PaymentMethod;
import com.example.smartshop.enums.PaymentStatus;
import com.example.smartshop.exception.BusinessRuleException;
import com.example.smartshop.exception.ResourceNotFoundException;
import com.example.smartshop.mapper.PaymentMapper;
import com.example.smartshop.repository.OrderRepository;
import com.example.smartshop.repository.PaymentRepository;
import com.example.smartshop.service.interfaces.PaymentService;
import com.springframework.stereotype.Service;
import com.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentMapper paymentMapper;

    private static final BigDecimal CASH_LIMIT = new BigDecimal("20000");

    @Override
    @Transactional
    public PaymentResponseDTO addPayment(Long orderId, PaymentRequestDTO request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        if (request.getPaymentMethod() == PaymentMethod.CASH) {
            if (request.getAmount().compareTo(CASH_LIMIT) > 0) {
                throw new BusinessRuleException(
                        "Cash payment exceeds legal limit of 20,000 DH (Art. 193 CGI). "
                        + "Amount: " + request.getAmount() + " DH");
            }
        }

        if (request.getAmount().compareTo(order.getRemainingAmount()) > 0) {
            throw new BusinessRuleException(
                    "Payment amount (" + request.getAmount() + " DH) exceeds remaining amount ("
                    + order.getRemainingAmount() + " DH)");
        }

        int nextPaymentNumber = order.getPayments().size() + 1;

        Payment payment = Payment.builder()
                .order(order)
                .paymentNumber(nextPaymentNumber)
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .status(PaymentStatus.EN_ATTENTE)
                .reference(request.getReference())
                .bankName(request.getBankName())
                .dueDate(request.getDueDate())
                .paymentDate(LocalDateTime.now())
                .build();

        Payment savedPayment = paymentRepository.save(payment);

        BigDecimal newRemainingAmount = order.getRemainingAmount().subtract(request.getAmount());
        order.setRemainingAmount(newRemainingAmount);
        orderRepository.save(order);

        return paymentMapper.toResponseDTO(savedPayment);
    }

    @Override
    public PaymentResponseDTO updatePaymentStatus(Long paymentId, PaymentStatus newStatus) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + paymentId));

        payment.setStatus(newStatus);

        if (newStatus == PaymentStatus.ENCAISSE && payment.getCollectionDate() == null) {
            payment.setCollectionDate(LocalDateTime.now());
        }

        Payment updated = paymentRepository.save(payment);
        return paymentMapper.toResponseDTO(updated);
    }

    @Override
    public List<PaymentResponseDTO> getOrderPayments(Long orderId) {
        List<Payment> payments = paymentRepository.findByOrderIdOrderByPaymentNumberAsc(orderId);
        return payments.stream()
                .map(paymentMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<PaymentResponseDTO> getPendingPayments() {
        List<Payment> payments = paymentRepository.findByStatus(PaymentStatus.EN_ATTENTE);
        return payments.stream()
                .map(paymentMapper::toResponseDTO)
                .toList();
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        return paymentMapper.toResponseDTO(payment);
    }
}

