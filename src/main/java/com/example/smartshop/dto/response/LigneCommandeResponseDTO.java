package com.example.smartshop.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeResponseDTO {

    private Long id;
    private Integer quantite;
    private BigDecimal prixUnitaireHt;
    private BigDecimal totalLigne;
    private Long commandeId;
    private Long produitId;
    private String nomProduit;
}
