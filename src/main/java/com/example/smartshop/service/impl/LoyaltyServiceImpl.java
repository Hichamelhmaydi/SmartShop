package com.example.smartshop.service.impl;

import com.example.smartshop.entity.Client;
import com.example.smartshop.enums.CustomerTier;
import com.example.smartshop.service.interfaces.LoyaltyService;
import com.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

@Service
public class LoyaltyServiceImpl implements LoyaltyService {

    @Override
    public CustomerTier calculateTier(Client client) {
        int totalOrders = client.getTotalOrders();
        BigDecimal totalSpent = client.getTotalSpent();

        if (totalOrders >= 20 || totalSpent.compareTo(new BigDecimal("15000")) >= 0) {
            return CustomerTier.PLATINUM;
        }

        if (totalOrders >= 10 || totalSpent.compareTo(new BigDecimal("5000")) >= 0) {
            return CustomerTier.GOLD;
        }

        if (totalOrders >= 3 || totalSpent.compareTo(new BigDecimal("1000")) >= 0) {
            return CustomerTier.SILVER;
        }

        return CustomerTier.BASIC;
    }

    @Override
    public BigDecimal calculateDiscount(CustomerTier tier, BigDecimal subtotalHT) {
        BigDecimal discount = BigDecimal.ZERO;

        switch (tier) {
            case SILVER:
                if (subtotalHT.compareTo(new BigDecimal("500")) >= 0) {
                    discount = subtotalHT.multiply(new BigDecimal("0.05"));
                }
                break;

            case GOLD:
                if (subtotalHT.compareTo(new BigDecimal("800")) >= 0) {
                    discount = subtotalHT.multiply(new BigDecimal("0.10"));
                }
                break;

            case PLATINUM:
                if (subtotalHT.compareTo(new BigDecimal("1200")) >= 0) {
                    discount = subtotalHT.multiply(new BigDecimal("0.15"));
                }
                break;

            case BASIC:
            default:
                discount = BigDecimal.ZERO;
                break;
        }

        return discount.setScale(2, RoundingMode.HALF_UP);
    }
}

