package org.example.smartshop.service;

import org.example.smartshop.dto.request.LoginRequestDTO;
import org.example.smartshop.dto.response.LoginResponseDTO;
import org.example.smartshop.entity.User;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO request);

    void logout();

    User getCurrentUser();

    boolean isAdmin();
}
