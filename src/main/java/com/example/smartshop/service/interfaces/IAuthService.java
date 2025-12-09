package com.example.smartshop.service.interfaces;

import com.example.smartshop.dto.request.AuthRequestDTO;
import com.example.smartshop.dto.response.AuthResponseDTO;
import jakarta.servlet.http.HttpSession;

public interface IAuthService {

    AuthResponseDTO login(AuthRequestDTO request, HttpSession session);

    void logout(HttpSession session);

    boolean isAuthenticated(HttpSession session);

    boolean isAdmin(HttpSession session);

    Long getCurrentUserId(HttpSession session);
}
