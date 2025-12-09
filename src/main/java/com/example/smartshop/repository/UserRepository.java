package com.example.smartshop.repository;

import com.example.smartshop.entity.User;
import com.example.smartshop.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByRole(UserRole role);

    @Query("SELECT u FROM User u WHERE u.id = :id AND u.role = :role")
    Optional<User> findByIdAndRole(@Param("id") Long id, @Param("role") UserRole role);
}
