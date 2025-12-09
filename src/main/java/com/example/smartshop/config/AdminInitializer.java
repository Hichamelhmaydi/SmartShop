package com.example.smartshop.config;

import com.example.smartshop.entity.Admin;
import com.example.smartshop.entity.User;
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
    public CommandLineRunner initAdmin() {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Admin admin = Admin.builder()
                    .username("admin")
                    .password(BCrypt.hashpw("admin123", BCrypt.gensalt()))
                    .role(UserRole.ADMIN)
                    .build();
                
                userRepository.save(admin);
                System.out.println("Admin user created successfully");
            }
        };
    }
}