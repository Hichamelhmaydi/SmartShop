package com.example.smartshop.service.interfaces;

import com.example.smartshop.dto.request.OrderRequestDTO;
import com.example.smartshop.dto.response.OrderResponseDTO;
import com.example.smartshop.dto.response.OrderSummaryResponseDTO;
import com.example.smartshop.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IOrderService {

    OrderResponseDTO createOrder(OrderRequestDTO request);

    OrderResponseDTO getOrderById(Long id);

    Page<OrderResponseDTO> getAllOrders(Pageable pageable);

    List<OrderSummaryResponseDTO> getClientOrders(Long clientId);

    OrderResponseDTO updateOrderStatus(Long id, OrderStatus status);

    OrderResponseDTO cancelOrder(Long id);

    OrderResponseDTO rejectOrder(Long id);

    Page<OrderResponseDTO> getOrdersByStatus(OrderStatus status, Pageable pageable);
}
