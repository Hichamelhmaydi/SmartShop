package com.example.smartshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Value("${app.vat-rate}")
    private double vatRate;
    
    @Value("${app.cash-payment-limit}")
    private double cashPaymentLimit;
    
    @Bean
    public Double vatRate() {
        return vatRate;
    }
    
    @Bean
    public Double cashPaymentLimit() {
        return cashPaymentLimit;
    }
}