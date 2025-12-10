package org.example.smartshop.service;

import org.example.smartshop.dto.request.ClientRequestDTO;
import org.example.smartshop.dto.response.ClientResponseDTO;
import org.example.smartshop.entity.Client;

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
