package com.example.smartshop.service.impl;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import com.example.smartshop.dto.request.LoginRequestDTO;
import com.example.smartshop.dto.response.LoginResponseDTO;
import com.example.smartshop.entity.User;
import com.example.smartshop.enums.UserRole;
import com.example.smartshop.exception.UnauthorizedException;
import com.example.smartshop.repository.UserRepository;
import com.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        httpSession.setAttribute("userId", user.getId());

        Long clientId = null;
        if (user.getRole() == UserRole.CLIENT && user.getClient() != null) {
            clientId = user.getClient().getId();
        }

        return LoginResponseDTO.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .clientId(clientId)
                .message("Login successful")
                .build();
    }

    @Override
    public void logout() {
        httpSession.invalidate();
    }

    @Override
    public User getCurrentUser() {
        Long userId = (Long) httpSession.getAttribute("userId");
        if (userId == null) {
            throw new UnauthorizedException("Not authenticated. Please login.");
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new UnauthorizedException("Session invalid. Please login again."));
    }

    @Override
    public boolean isAdmin() {
        User user = getCurrentUser();
        return user.getRole() == UserRole.ADMIN;
    }
}
