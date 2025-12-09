package com.example.smartshop.entity;

import com.example.smartshop.enums.CustomerTier;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

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
    private CustomerTier customerTier = CustomerTier.BASIC;

    @Column(nullable = false)
    private int totalOrders = 0;

    @Column(nullable = false)
    private double totalSpent = 0.0;

    private LocalDateTime firstOrderDate;
    private LocalDateTime lastOrderDate;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;
    public Long getId() {
        return super.getId();
    }
    
    public String getUsername() {
        return super.getUsername();
    }
    
    public List<Order> getOrders() {
        return orders;
    }
}