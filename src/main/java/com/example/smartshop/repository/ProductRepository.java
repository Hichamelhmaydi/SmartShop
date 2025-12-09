package com.example.smartshop.repository;

import com.example.smartshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT p FROM Product p WHERE p.deleted = false")
    Page<Product> findAllActive(Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE p.deleted = false AND p.name LIKE %:name%")
    Page<Product> findByNameContaining(String name, Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE p.deleted = false AND p.stock > 0")
    List<Product> findAvailableProducts();
    
    Optional<Product> findByIdAndDeletedFalse(Long id);
}