package com.example.smartshop.mapper;

import com.example.smartshop.dto.request.OrderItemRequestDTO;
import com.example.smartshop.dto.response.OrderItemResponseDTO;
import com.example.smartshop.entity.Order;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommandeMapper {

    Order toEntity(OrderItemRequestDTO dto);

    OrderItemResponseDTO toDto(Order entity);

    List<OrderItemResponseDTO> toDtoList(List<Order> entities);
}
