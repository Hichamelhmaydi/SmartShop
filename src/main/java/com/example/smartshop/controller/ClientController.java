package com.example.smartshop.controller;

import lombok.RequiredArgsConstructor;

import com.example.smartshop.dto.request.ClientRequestDTO;
import com.example.smartshop.dto.response.ClientResponseDTO;
import com.example.smartshop.dto.response.OrderResponseDTO;
import com.example.smartshop.exception.UnauthorizedException;
import com.example.smartshop.service.AuthService;
import com.example.smartshop.service.ClientService;
import com.example.smartshop.service.OrderService;
import com.springframework.http.HttpStatus;
import com.springframework.http.ResponseEntity;
import com.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final OrderService orderService;
    private final AuthService authService;


    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO request) {
        if (!authService.isAdmin()) {
            throw new UnauthorizedException("Only ADMIN can create clients");
        }

        ClientResponseDTO response = clientService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id) {
        if (!authService.isAdmin()) {

            Long currentClientId = authService.getCurrentUser().getClient().getId();
            if (!currentClientId.equals(id)) {
                throw new UnauthorizedException("Clients can only view their own data");
            }
        }

        ClientResponseDTO response = clientService.getClientById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        if (!authService.isAdmin()) {
            throw new UnauthorizedException("Only ADMIN can view all clients");
        }

        List<ClientResponseDTO> response = clientService.getAllClients();
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(
            @PathVariable Long id,
            @Valid @RequestBody ClientRequestDTO request) {

        if (!authService.isAdmin()) {
            throw new UnauthorizedException("Only ADMIN can update clients");
        }

        ClientResponseDTO response = clientService.updateClient(id, request);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        if (!authService.isAdmin()) {
            throw new UnauthorizedException("Only ADMIN can delete clients");
        }

        clientService.deleteClient(id);
        return ResponseEntity.ok("Client deleted successfully");
    }


    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderResponseDTO>> getClientOrders(@PathVariable Long id) {
        if (!authService.isAdmin()) {
            Long currentClientId = authService.getCurrentUser().getClient().getId();
            if (!currentClientId.equals(id)) {
                throw new UnauthorizedException("Clients can only view their own orders");
            }
        }

        List<OrderResponseDTO> response = orderService.getClientOrders(id);
        return ResponseEntity.ok(response);
    }
}
