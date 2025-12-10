package com.example.smartshop.service.interfaces;

import com.example.smartshop.entity.Order;
import com.example.smartshop.entity.PromoCode;

import java.util.List;

public interface PromoCodeService {

    PromoCode createPromoCode(String code);

    void validateForUse(String code);

    void markAsUsed(String code, Order order);

    List<PromoCode> getAllPromoCodes();

}