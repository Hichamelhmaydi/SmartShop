package com.example.smartshop.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeRequestDTO {
    private Long produitId;
    private Integer quantite;
}

