package com.example.smartshop.service.impl;

import com.example.smartshop.dto.request.AuthRequestDTO;
import com.example.smartshop.dto.response.AuthResponseDTO;
import com.example.smartshop.entity.User;
import com.example.smartshop.enums.UserRole;
import com.example.smartshop.exception.UnauthorizedException;
import com.example.smartshop.repository.UserRepository;
import com.example.smartshop.service.interfaces.IAuthService;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthResponseDTO login(AuthRequestDTO request, HttpSession session) {
        // Récupérer l'utilisateur par son username
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        // Vérifier si l'utilisateur existe et si le mot de passe correspond
        if (userOpt.isEmpty()) {
            throw new UnauthorizedException("Invalid username or password");
        }

        User user = userOpt.get();

        boolean passwordMatches = BCrypt.checkpw(request.getPassword(), user.getPassword());
        if (!passwordMatches) {
            throw new UnauthorizedException("Invalid username or password");
        }

        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole().toString());

        // Créer la réponse
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
        Object roleObj = session.getAttribute("role");
        return roleObj != null && roleObj.toString().equals("ADMIN");
    }

    @Override
    public Long getCurrentUserId(HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return null;
        }

        try {
            if (userIdObj instanceof Long) {
                return (Long) userIdObj;
            } else if (userIdObj instanceof Integer) {
                return ((Integer) userIdObj).longValue();
            } else if (userIdObj instanceof String) {
                return Long.parseLong((String) userIdObj);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
