package org.example.smartshop.service;

import org.example.smartshop.dto.request.OrderRequestDTO;
import org.example.smartshop.dto.response.OrderResponseDTO;
import org.example.smartshop.entity.Order;

import java.util.List;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO request);

    OrderResponseDTO confirmOrder(Long orderId);

    OrderResponseDTO cancelOrder(Long orderId);

    OrderResponseDTO getOrderById(Long id);

    List<OrderResponseDTO> getAllOrders();

    List<OrderResponseDTO> getClientOrders(Long clientId);

    Order getOrderEntity(Long id);
}
