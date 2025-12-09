package com.example.smartshop.dto.response;

import com.example.smartshop.enums.UserRole;
import lombok.Data;

@Data
public class AuthResponseDTO {
    private Long id;
    private String username;
    private UserRole role;
    private String message;
}