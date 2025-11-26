package com.example.smartshop.entity;

import com.example.smartshop.enums.StatutCommande;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "commandes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dateCreation;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sousTotalHt;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montantReduction;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montantTva;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalTtc;

    @Column(nullable = false)
    private String codePromo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutCommande statut = StatutCommande.PENDING;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montantRestant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<LigneCommande> lignesCommande;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Paiement> paiements;

}
