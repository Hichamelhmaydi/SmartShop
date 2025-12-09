package com.example.smartshop.config;

import com.example.smartshop.entity.Admin;
import com.example.smartshop.enums.UserRole;
import com.example.smartshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserRepository userRepository;

    @Bean
    @Transactional
    public CommandLineRunner initializeAdmin() {
        return args -> {
            boolean adminExists = userRepository.existsByRole(UserRole.ADMIN);
            
            if (!adminExists) {
                String hashedPassword = BCrypt.hashpw("admin123", BCrypt.gensalt());
                
                Admin admin = Admin.builder()
                        .username("admin")
                        .password(hashedPassword)
                        .role(UserRole.ADMIN)
                        .build();
                
                userRepository.save(admin);
                System.out.println("✓ Admin user created successfully");
                System.out.println("Username: admin");
                System.out.println("Password: admin123");
            } else {
                System.out.println("✓ Admin user already exists");
            }
        };
    }
}