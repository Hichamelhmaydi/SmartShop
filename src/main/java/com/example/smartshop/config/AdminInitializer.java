package com.example.smartshop.config;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.smartshop.entity.Admin;
import com.example.smartshop.enums.UserRole;
import com.example.smartshop.repository.AdminRepository;

@Configuration
public class AdminInitializer {

    @Bean
    public CommandLineRunner creeAdminParCommandLineRunner(AdminRepository adminRepository) {
        return args -> {
            String defaultUsername = "admin";
            String defaultPassword = "admin123";

            adminRepository.findByUsername(defaultUsername).orElseGet(() -> {
                String hashedPassword = BCrypt.hashpw(defaultPassword, BCrypt.gensalt());
                Admin admin = Admin.builder()
                        .username(defaultUsername)
                        .password(hashedPassword)
                        .role(UserRole.ADMIN)
                        .build();
                return adminRepository.save(admin);
            });
        };
    }
}
