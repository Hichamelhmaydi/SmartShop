package com.example.smartshop.mapper;

import com.example.smartshop.dto.request.ProductRequestDTO;
import com.example.smartshop.dto.response.ProductResponseDTO;
import com.example.smartshop.entity.Product;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    Product toEntity(ProductRequestDTO dto);

    ProductResponseDTO toDto(Product entity);

    List<ProductResponseDTO> toDtoList(List<Product> entities);
}
