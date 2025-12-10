package com.example.smartshop.mapper;

import com.example.smartshop.dto.response.PaymentResponseDTO;
import com.example.smartshop.entity.Payment;
import com.mapstruct.Mapper;
import com.mapstruct.Mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "order.id", target = "orderId")
    PaymentResponseDTO toResponseDTO(Payment payment);

    List<PaymentResponseDTO> toResponseDTOList(List<Payment> payments);
}
