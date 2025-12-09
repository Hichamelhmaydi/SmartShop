package com.example.smartshop.repository;

import com.example.smartshop.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    
    @Query("SELECT c FROM Client c WHERE c.deleted = false")
    List<Client> findAllActive();
}