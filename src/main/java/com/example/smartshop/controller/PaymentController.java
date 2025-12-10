package com.example.smartshop.controller;

import lombok.RequiredArgsConstructor;

import com.example.smartshop.dto.request.PaymentRequestDTO;
import com.example.smartshop.dto.response.PaymentResponseDTO;
import com.example.smartshop.enums.PaymentStatus;
import com.example.smartshop.exception.UnauthorizedException;
import com.example.smartshop.service.AuthService;
import com.example.smartshop.service.PaymentService;
import com.springframework.http.HttpStatus;
import com.springframework.http.ResponseEntity;
import com.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final AuthService authService;

    
    @PostMapping("/orders/{orderId}/payments")
    public ResponseEntity<PaymentResponseDTO> addPayment(
            @PathVariable Long orderId,
            @Valid @RequestBody PaymentRequestDTO request) {

        if (!authService.isAdmin()) {
            throw new UnauthorizedException("Only ADMIN can add payments");
        }

        PaymentResponseDTO response = paymentService.addPayment(orderId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/orders/{orderId}/payments")
    public ResponseEntity<List<PaymentResponseDTO>> getOrderPayments(@PathVariable Long orderId) {
        
        List<PaymentResponseDTO> response = paymentService.getOrderPayments(orderId);
        return ResponseEntity.ok(response);
    }

 
    @PutMapping("/payments/{id}/status")
    public ResponseEntity<PaymentResponseDTO> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam PaymentStatus status) {

        if (!authService.isAdmin()) {
            throw new UnauthorizedException("Only ADMIN can update payment status");
        }

        PaymentResponseDTO response = paymentService.updatePaymentStatus(id, status);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/payments/pending")
    public ResponseEntity<List<PaymentResponseDTO>> getPendingPayments() {
        if (!authService.isAdmin()) {
            throw new UnauthorizedException("Only ADMIN can view pending payments");
        }

        List<PaymentResponseDTO> response = paymentService.getPendingPayments();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/payments/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable Long id) {
        if (!authService.isAdmin()) {
            throw new UnauthorizedException("Only ADMIN can view payment details");
        }

        PaymentResponseDTO response = paymentService.getPaymentById(id);
        return ResponseEntity.ok(response);
    }
}
