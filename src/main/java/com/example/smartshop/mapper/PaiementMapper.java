package com.example.smartshop.mapper;

import com.example.smartshop.dto.request.PaymentRequestDTO;
import com.example.smartshop.dto.response.PaymentResponseDTO;
import com.example.smartshop.entity.Payment;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PaiementMapper {

    Payment toEntity(PaymentRequestDTO dto);

    PaymentResponseDTO toDto(Payment entity);

    List<PaymentResponseDTO> toDtoList(List<Payment> entities);
}
