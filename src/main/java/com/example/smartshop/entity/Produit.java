package com.example.smartshop.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "produits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private Double prixHt;
    @Column(nullable = false)
    private int stock;
    @Column(name = "supprime")
    private Boolean supprime;
    @Column(nullable = false)
    private LocalDateTime dateCreation;
    @Column(nullable = false)
    private LocalDateTime dateModification;
}
