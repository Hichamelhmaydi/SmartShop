package org.example.smartshop.mapper;

import org.example.smartshop.dto.response.OrderItemResponseDTO;
import org.example.smartshop.dto.response.OrderResponseDTO;
import org.example.smartshop.entity.Order;
import org.example.smartshop.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.name", target = "clientName")
    @Mapping(source = "orderItems", target = "items")
    OrderResponseDTO toResponseDTO(Order order);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrderItemResponseDTO toOrderItemResponseDTO(OrderItem orderItem);

    List<OrderItemResponseDTO> toOrderItemResponseDTOList(List<OrderItem> orderItems);
}
