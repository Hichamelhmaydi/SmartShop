package com.example.smartshop.mapper;

import com.example.smartshop.dto.response.OrderItemResponseDTO;
import com.example.smartshop.dto.response.OrderResponseDTO;
import com.example.smartshop.entity.Order;
import com.example.smartshop.entity.OrderItem;
import com.mapstruct.Mapper;
import com.mapstruct.Mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
