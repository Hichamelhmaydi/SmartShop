package com.example.smartshop.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import com.example.smartshop.enums.StatutCommande;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeResponseDTO {

    private Long id;
    private LocalDate dateCreation;
    private BigDecimal sousTotalHt;
    private BigDecimal montantReduction;
    private BigDecimal montantTva;
    private BigDecimal totalTtc;
    private String codePromo;
    private StatutCommande statut;
    private BigDecimal montantRestant;
    private Long clientId;
    private List<LigneCommandeResponseDTO> lignesCommande;
}
