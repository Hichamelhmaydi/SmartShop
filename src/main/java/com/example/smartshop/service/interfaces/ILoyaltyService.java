package com.example.smartshop.service.interfaces;

import com.example.smartshop.entity.Client;
import com.example.smartshop.enums.CustomerTier;
import java.math.BigDecimal;

public interface ILoyaltyService {

    CustomerTier calculateTier(Client client);

    BigDecimal calculateDiscount(Client client, BigDecimal subtotal);

    void updateClientTier(Client client);

    void updateClientStats(Client client, BigDecimal orderAmount);
}
