package com.example.smartshop.dto.response;

import java.time.LocalTime;
import com.example.smartshop.enums.UserRole;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String niveauFidelite;
    private int totalCommandes;
    private double totalDepenses;
    private LocalTime premiereCommande;
    private LocalTime derniereCommande;
    private UserRole role;
}
