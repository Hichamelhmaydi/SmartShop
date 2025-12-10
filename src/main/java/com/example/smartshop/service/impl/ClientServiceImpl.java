package com.example.smartshop.service.impl;

import lombok.RequiredArgsConstructor;
import com.example.smartshop.dto.request.ClientRequestDTO;
import com.example.smartshop.dto.response.ClientResponseDTO;
import com.example.smartshop.entity.Client;
import com.example.smartshop.entity.User;
import com.example.smartshop.enums.CustomerTier;
import com.example.smartshop.enums.UserRole;
import com.example.smartshop.exception.BusinessRuleException;
import com.example.smartshop.exception.ResourceNotFoundException;
import com.example.smartshop.mapper.ClientMapper;
import com.example.smartshop.repository.ClientRepository;
import com.example.smartshop.repository.UserRepository;
import com.example.smartshop.service.interfaces.ClientService;
import com.springframework.stereotype.Service;
import com.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final ClientMapper clientMapper;
    private final LoyaltyService loyaltyService;

    @Override
    public ClientResponseDTO createClient(ClientRequestDTO request) {
        if (clientRepository.existsByEmail(request.getEmail())) {
            throw new BusinessRuleException("Email already registered: " + request.getEmail());
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BusinessRuleException("Username already taken: " + request.getUsername());
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .role(UserRole.CLIENT)
                .build();

        User savedUser = userRepository.save(user);

        Client client = clientMapper.toEntity(request);
        client.setUser(savedUser);
        client.setTier(CustomerTier.BASIC);

        Client savedClient = clientRepository.save(client);

        return clientMapper.toResponseDTO(savedClient);
    }

    @Override
    public ClientResponseDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
        return clientMapper.toResponseDTO(client);
    }

    @Override
    public List<ClientResponseDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponseDTO updateClient(Long id, ClientRequestDTO request) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));

        if (!client.getEmail().equals(request.getEmail())
                && clientRepository.existsByEmail(request.getEmail())) {
            throw new BusinessRuleException("Email already registered: " + request.getEmail());
        }

        clientMapper.updateEntityFromDTO(request, client);
        Client updated = clientRepository.save(client);
        return clientMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
        clientRepository.delete(client);
    }

    @Override
    public void updateClientStatsAfterOrderConfirmation(Client client, BigDecimal orderTotalTTC) {
        client.setTotalOrders(client.getTotalOrders() + 1);
        client.setTotalSpent(client.getTotalSpent().add(orderTotalTTC));

        client.setLastOrderDate(LocalDateTime.now());
        if (client.getFirstOrderDate() == null) {
            client.setFirstOrderDate(LocalDateTime.now());
        }

        CustomerTier newTier = loyaltyService.calculateTier(client);
        client.setTier(newTier);

        clientRepository.save(client);
    }

    @Override
    public Client getClientEntity(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
    }

    @Override
    public void updateClientStatsAfterOrderConfirmation(ch.qos.logback.core.net.server.Client client,
            BigDecimal orderTotalTTC) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateClientStatsAfterOrderConfirmation'");
    }
}

