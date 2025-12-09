package com.example.smartshop.repository;

import com.example.smartshop.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    List<Payment> findByOrderId(Long orderId);
    
    @Query("SELECT p FROM Payment p WHERE p.order.id = :orderId ORDER BY p.paymentNumber")
    List<Payment> findPaymentsByOrder(@Param("orderId") Long orderId);
    
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.order.id = :orderId AND p.status = 'SETTLED'")
    BigDecimal sumSettledPaymentsByOrder(@Param("orderId") Long orderId);
    
    @Query("SELECT MAX(p.paymentNumber) FROM Payment p WHERE p.order.id = :orderId")
    Integer findMaxPaymentNumberByOrder(@Param("orderId") Long orderId);
}