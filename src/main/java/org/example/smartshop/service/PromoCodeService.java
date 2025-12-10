package org.example.smartshop.service;

import org.example.smartshop.entity.Order;
import org.example.smartshop.entity.PromoCode;

import java.util.List;

public interface PromoCodeService {

    PromoCode createPromoCode(String code);

    void validateForUse(String code);

    void markAsUsed(String code, Order order);

    List<PromoCode> getAllPromoCodes();

}
