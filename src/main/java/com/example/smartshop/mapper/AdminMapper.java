package com.example.smartshop.mapper;

import org.mapstruct.Mapper;
import com.example.smartshop.dto.request.AdminRequesteDTO;
import com.example.smartshop.dto.response.AdminResponseDTO;
import com.example.smartshop.entity.Admin;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin toEntity(AdminRequesteDTO dto);

    AdminResponseDTO toDto(Admin entity);
}
