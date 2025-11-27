package com.example.smartshop.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProduitRequestDTO {

    @NotBlank
    private String nom;

    @NotNull
    @DecimalMin(value = "0.1", inclusive = false)
    private BigDecimal prixHt;

    @NotNull
    @PositiveOrZero
    private Integer stock;
}
