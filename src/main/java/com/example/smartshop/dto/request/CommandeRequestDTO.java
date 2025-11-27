package com.example.smartshop.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeRequestDTO {

    private Long clientId;
    private List<LigneCommandeRequestDTO> lignesCommande;
    private String codePromo;
}
