package com.example.smartshop.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProduitResponseDTO {

    private Long id;
    private String nom;
    private BigDecimal prixHt;
    private Integer stock;
    private LocalDateTime dateCreation;
}
