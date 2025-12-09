package com.example.smartshop.service.interfaces;

import com.example.smartshop.dto.request.ClientRequestDTO;
import com.example.smartshop.dto.response.ClientResponseDTO;
import com.example.smartshop.dto.response.ClientStatsResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IClientService {

    ClientResponseDTO createClient(ClientRequestDTO request);

    ClientResponseDTO getClientById(Long id);

    Page<ClientResponseDTO> getAllClients(Pageable pageable);

    ClientResponseDTO updateClient(Long id, ClientRequestDTO request);

    void deleteClient(Long id);

    ClientStatsResponseDTO getClientStats(Long clientId);

    List<ClientResponseDTO> searchClients(String keyword);
}
