package com.example.smartshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.smartshop.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUsername(String username);
}
