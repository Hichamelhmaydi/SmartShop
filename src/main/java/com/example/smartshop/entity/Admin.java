package com.example.smartshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "admin")
@Data
@AllArgsConstructor
public class Admin extends User {

}
