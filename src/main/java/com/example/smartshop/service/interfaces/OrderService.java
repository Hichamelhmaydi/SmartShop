package com.example.smartshop.service.interfaces;

import com.example.smartshop.dto.request.OrderRequestDTO;
import com.example.smartshop.dto.response.OrderResponseDTO;
import com.example.smartshop.entity.Order;
import com.example.smartshop.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
