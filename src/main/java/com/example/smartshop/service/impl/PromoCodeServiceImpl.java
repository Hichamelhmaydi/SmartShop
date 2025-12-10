package com.example.smartshop.service.impl;

import lombok.RequiredArgsConstructor;
import com.example.smartshop.entity.Order;
import com.example.smartshop.entity.PromoCode;
import com.example.smartshop.exception.BusinessRuleException;
import com.example.smartshop.repository.PromoCodeRepository;
import com.example.smartshop.service.interfaces.PromoCodeService;
import com.springframework.stereotype.Service;
import com.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PromoCodeServiceImpl implements PromoCodeService {

    private final PromoCodeRepository promoCodeRepository;

    @Override
    public PromoCode createPromoCode(String code) {
        if (code == null || !code.matches("^PROMO-[A-Z0-9]{4}$")) {
            throw new BusinessRuleException("Invalid promo code format. Must be PROMO-XXXX");
        }
        if (promoCodeRepository.existsByCode(code)) {
            throw new BusinessRuleException("Promo code already exists: " + code);
        }

        PromoCode promo = PromoCode.builder()
                .code(code)
                .used(false)
                .build();

        return promoCodeRepository.save(promo);
    }

    @Override
    public void validateForUse(String code) {
        if (code == null || !code.matches("^PROMO-[A-Z0-9]{4}$")) {
            throw new BusinessRuleException("Invalid promo code format: " + code);
        }

        PromoCode promo = promoCodeRepository.findByCode(code)
                .orElseThrow(() -> new BusinessRuleException("Invalid promo code: " + code));

        if (Boolean.TRUE.equals(promo.getUsed())) {
            throw new BusinessRuleException("Promo code already used: " + code);
        }
    }

    @Override
    public void markAsUsed(String code, Order order) {
        PromoCode promo = promoCodeRepository.findByCode(code)
                .orElseThrow(() -> new BusinessRuleException("Promo code not found: " + code));

        promo.setUsed(true);
        promo.setUsedAt(LocalDateTime.now());
        promo.setOrder(order);

        promoCodeRepository.save(promo);
    }

    @Override
    public List<PromoCode> getAllPromoCodes() {
        return promoCodeRepository.findAll();
    }

}
