package com.example.smartshop.service.interfaces;

import com.example.smartshop.entity.Client;
import com.example.smartshop.enums.CustomerTier;
import java.math.BigDecimal;

public interface LoyaltyService {

    CustomerTier calculateTier(Client client);

    BigDecimal calculateDiscount(CustomerTier tier, BigDecimal subtotalHT);
}
