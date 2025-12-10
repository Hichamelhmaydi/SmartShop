package com.example.smartshop.controller;

import lombok.RequiredArgsConstructor;

import com.example.smartshop.entity.PromoCode;
import com.example.smartshop.exception.BusinessRuleException;
import com.example.smartshop.service.AuthService;
import com.example.smartshop.service.PromoCodeService;
import com.springframework.http.ResponseEntity;
import com.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/promo-codes")
@RequiredArgsConstructor
public class PromoCodeController {

    private final PromoCodeService promoCodeService;
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<?> createPromoCode(@RequestBody Map<String, String> request) {
        if (!authService.isAdmin()) {
            return ResponseEntity.status(403).body(Map.of("error", "Only ADMIN can create promo codes"));
        }

        String code = request.get("code");
        if (code == null) {
            throw new BusinessRuleException("Promo code is required");
        }

        PromoCode created = promoCodeService.createPromoCode(code.trim());
        return ResponseEntity.ok(Map.of(
                "id", created.getId(),
                "code", created.getCode(),
                "used", created.getUsed(),
                "discount", "5%"
        ));
    }

    @GetMapping
    public ResponseEntity<?> getAllPromoCodes() {
        if (!authService.isAdmin()) {
            return ResponseEntity.status(403).body(Map.of("error", "Only ADMIN can view promo codes"));
        }

     
        List<PromoCode> codes = promoCodeService.getAllPromoCodes(); 
        return ResponseEntity.ok(codes);
    }
}
