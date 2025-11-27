package com.example.smartshop.mapper;

import com.example.smartshop.dto.request.LigneCommandeRequestDTO;
import com.example.smartshop.dto.response.LigneCommandeResponseDTO;
import com.example.smartshop.entity.LigneCommande;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")

public interface LigneCommandeMapper {

    LigneCommande toEntity(LigneCommandeRequestDTO dto);

    LigneCommandeResponseDTO toDto(LigneCommande entity);

    List<LigneCommandeResponseDTO> toDtoList(List<LigneCommande> entities);
}
