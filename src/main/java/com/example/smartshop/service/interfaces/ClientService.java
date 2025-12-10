package com.example.smartshop.service.interfaces;

import com.example.smartshop.dto.request.ClientRequestDTO;
import com.example.smartshop.dto.response.ClientResponseDTO;

import ch.qos.logback.core.net.server.Client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {

    ClientResponseDTO createClient(ClientRequestDTO request);

    ClientResponseDTO getClientById(Long id);

    List<ClientResponseDTO> getAllClients();

    ClientResponseDTO updateClient(Long id, ClientRequestDTO request);

    void deleteClient(Long id);

    void updateClientStatsAfterOrderConfirmation(Client client, BigDecimal orderTotalTTC);

    Client getClientEntity(Long id);
}
