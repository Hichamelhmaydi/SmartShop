package org.example.smartshop.service;

import org.example.smartshop.entity.Client;
import org.example.smartshop.enums.CustomerTier;

import java.math.BigDecimal;

public interface LoyaltyService {

    CustomerTier calculateTier(Client client);

    BigDecimal calculateDiscount(CustomerTier tier, BigDecimal subtotalHT);
}
