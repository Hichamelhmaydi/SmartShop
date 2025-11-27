package com.example.smartshop.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.example.smartshop.enums.TypePaiement;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaiementRequestDTO {

    private Long commandeId;
    private BigDecimal montant;
    private TypePaiement typePaiement;
    private String reference;
    private String banque;
    private LocalDate dateEcheance;
}
