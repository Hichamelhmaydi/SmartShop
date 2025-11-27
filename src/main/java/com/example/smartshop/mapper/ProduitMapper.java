package com.example.smartshop.mapper;

import com.example.smartshop.dto.request.ProduitRequestDTO;
import com.example.smartshop.dto.response.ProduitResponseDTO;
import com.example.smartshop.entity.Produit;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    Produit toEntity(ProduitRequestDTO dto);

    ProduitResponseDTO toDto(Produit entity);

    List<ProduitResponseDTO> toDtoList(List<Produit> entities);
}
