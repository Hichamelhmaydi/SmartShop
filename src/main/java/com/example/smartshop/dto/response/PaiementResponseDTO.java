package com.example.smartshop.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.example.smartshop.enums.TypePaiement;
import com.example.smartshop.enums.StatutPaiement;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaiementResponseDTO {

    private Long id;
    private Integer numeroPaiement;
    private BigDecimal montant;
    private TypePaiement typePaiement;
    private StatutPaiement statut;
    private LocalDate datePaiement;
    private LocalDate dateEncaissement;
    private String reference;
    private String banque;
    private LocalDate dateEcheance;
    private Long commandeId;
}
