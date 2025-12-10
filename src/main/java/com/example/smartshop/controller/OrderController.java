package com.example.smartshop.controller;

import lombok.RequiredArgsConstructor;

import com.example.smartshop.dto.request.OrderRequestDTO;
import com.example.smartshop.dto.response.OrderResponseDTO;
import com.example.smartshop.exception.UnauthorizedException;
import com.example.smartshop.service.AuthService;
import com.example.smartshop.service.OrderService;
import com.springframework.http.HttpStatus;
import com.springframework.http.ResponseEntity;
import com.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartshop.service.interfaces.AuthService;
import com.example.smartshop.service.interfaces.OrderService;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final AuthService authService;


    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO request) {
        if (!authService.isAdmin()) {
            throw new UnauthorizedException("Only ADMIN can create orders");
        }

        OrderResponseDTO response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        OrderResponseDTO order = orderService.getOrderById(id);

        if (!authService.isAdmin()) {
            Long currentClientId = authService.getCurrentUser().getClient().getId();
            if (!currentClientId.equals(order.getClientId())) {
                throw new UnauthorizedException("Clients can only view their own orders");
            }
        }

        return ResponseEntity.ok(order);
    }

 
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        if (!authService.isAdmin()) {
            throw new UnauthorizedException("Only ADMIN can view all orders");
        }

        List<OrderResponseDTO> response = orderService.getAllOrders();
        return ResponseEntity.ok(response);
    }

   
    @PutMapping("/{id}/confirm")
    public ResponseEntity<OrderResponseDTO> confirmOrder(@PathVariable Long id) {
        if (!authService.isAdmin()) {
            throw new UnauthorizedException("Only ADMIN can confirm orders");
        }

        OrderResponseDTO response = orderService.confirmOrder(id);
        return ResponseEntity.ok(response);
    }

   
    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Long id) {
        if (!authService.isAdmin()) {
            throw new UnauthorizedException("Only ADMIN can cancel orders");
        }

        OrderResponseDTO response = orderService.cancelOrder(id);
        return ResponseEntity.ok(response);
    }
}
