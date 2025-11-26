package com.example.smartshop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

import com.example.smartshop.enums.NiveauFidelite;

@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends User {

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private NiveauFidelite niveauFidelite = NiveauFidelite.BASIC;
    @Column(nullable = false)
    private int totalCommandes;
    @Column(nullable = false)
    private double totalDepenses;
    @Column(nullable = false)
    private LocalTime premiereCommande;
    @Column(nullable = false)
    private LocalTime derniereCommande;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commande> commandes;
}
