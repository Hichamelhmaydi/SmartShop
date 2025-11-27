package com.example.smartshop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

import com.example.smartshop.enums.NiveauFidelite;
import com.example.smartshop.enums.UserRole;

@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends User {

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NiveauFidelite niveauFidelite = NiveauFidelite.BASIC;

    @Column(nullable = false)
    private int totalCommandes = 0;

    @Column(nullable = false)
    private double totalDepenses = 0;

    private LocalTime premiereCommande;
    private LocalTime derniereCommande;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commande> commandes;
}
