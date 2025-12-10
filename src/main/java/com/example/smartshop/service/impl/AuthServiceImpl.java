package com.example.smartshop.service.impl;

import com.example.smartshop.dto.request.AuthRequestDTO;
import com.example.smartshop.dto.response.AuthResponseDTO;
import com.example.smartshop.entity.User;
import com.example.smartshop.enums.UserRole;
import com.example.smartshop.exception.UnauthorizedException;
import com.example.smartshop.repository.UserRepository;
import com.example.smartshop.service.interfaces.IAuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;

    @Override
    public AuthResponseDTO login(AuthRequestDTO request, HttpSession session) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt.isEmpty() || !BCrypt.checkpw(request.getPassword(), userOpt.get().getPassword())) {
            throw new UnauthorizedException("Invalid username or password");
        }

        User user = userOpt.get();

        session.setAttribute("userId", user.getId());
        AuthResponseDTO response = new AuthResponseDTO();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        response.setMessage("Login successful");

        return response;
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @Override
    public boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("userId") != null;
    }

    @Override
    public boolean isAdmin(HttpSession session) {
        return UserRole.ADMIN.name().equals(session.getAttribute("role"));
    }

    @Override
    public Long getCurrentUserId(HttpSession session) {
        Object userId = session.getAttribute("userId");
        return userId != null ? Long.parseLong(userId.toString()) : null;
    }
}
