package com.example.smartshop.mapper;

import com.example.smartshop.dto.request.ClientRequestDTO;
import com.example.smartshop.dto.response.ClientResponseDTO;
import com.example.smartshop.entity.Client;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientRequestDTO dto);
    ClientResponseDTO toDto(Client entity);
    List<ClientResponseDTO> toDtoList(List<Client> entities);
}
