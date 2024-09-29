package com.test.cnss.myProject.service;

import java.util.List;

import com.test.cnss.myProject.model.PaymentMethod;

public interface PaymentMethodService {
    PaymentMethod addPaymentMethod(PaymentMethod paymentMethod);
    
    PaymentMethod getPaymentMethodById(Long id);
    
    PaymentMethod updatePaymentMethod(Long id, PaymentMethod paymentMethod);
    
    void deactivatePaymentMethod(Long id);
    
    List<PaymentMethod> getAllPaymentMethods();
}