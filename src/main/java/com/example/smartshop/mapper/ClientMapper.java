package com.example.smartshop.mapper;

import com.example.smartshop.dto.request.ClientRequestDTO;
import com.example.smartshop.dto.response.ClientResponseDTO;
import com.example.smartshop.dto.response.ClientStatsResponseDTO;
import com.example.smartshop.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "customerTier", ignore = true)
    @Mapping(target = "totalOrders", ignore = true)
    @Mapping(target = "totalSpent", ignore = true)
    @Mapping(target = "firstOrderDate", ignore = true)
    @Mapping(target = "lastOrderDate", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    Client toEntity(ClientRequestDTO dto);

    @Mapping(source = "customerTier", target = "tier")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    ClientResponseDTO toDto(Client entity);

    List<ClientResponseDTO> toDtoList(List<Client> entities);
    
    @Mapping(source = "id", target = "clientId")
    @Mapping(source = "username", target = "clientName")
    ClientStatsResponseDTO toStatsDto(Client entity);
}