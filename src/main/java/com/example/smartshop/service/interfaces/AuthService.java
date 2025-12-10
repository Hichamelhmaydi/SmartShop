package com.example.smartshop.service.interfaces;


import org.apache.catalina.User;

import com.example.smartshop.dto.request.LoginRequestDTO;
import com.example.smartshop.dto.response.LoginResponseDTO;

import jakarta.servlet.http.HttpSession;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO request);

    void logout();

    User getCurrentUser();

    boolean isAdmin();
}
