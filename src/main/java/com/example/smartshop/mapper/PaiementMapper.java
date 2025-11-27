package com.example.smartshop.mapper;

import com.example.smartshop.dto.request.PaiementRequestDTO;
import com.example.smartshop.dto.response.PaiementResponseDTO;
import com.example.smartshop.entity.Paiement;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PaiementMapper {

    Paiement toEntity(PaiementRequestDTO dto);

    PaiementResponseDTO toDto(Paiement entity);

    List<PaiementResponseDTO> toDtoList(List<Paiement> entities);
}
