package com.example.smartshop.mapper;

import com.example.smartshop.dto.request.CommandeRequestDTO;
import com.example.smartshop.dto.response.CommandeResponseDTO;
import com.example.smartshop.entity.Commande;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommandeMapper {

    Commande toEntity(CommandeRequestDTO dto);

    CommandeResponseDTO toDto(Commande entity);

    List<CommandeResponseDTO> toDtoList(List<Commande> entities);
}
