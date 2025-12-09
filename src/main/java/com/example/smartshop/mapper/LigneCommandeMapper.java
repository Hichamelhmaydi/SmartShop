package com.example.smartshop.mapper;

import com.example.smartshop.dto.request.OrderItemRequestDTO;
import com.example.smartshop.dto.response.OrderItemResponseDTO;
import com.example.smartshop.entity.OrderItem;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")

public interface LigneCommandeMapper {

    OrderItem toEntity(OrderItemRequestDTO dto);

    OrderItemResponseDTO toDto(OrderItem entity);

    List<OrderItemResponseDTO> toDtoList(List<OrderItem> entities);
}
