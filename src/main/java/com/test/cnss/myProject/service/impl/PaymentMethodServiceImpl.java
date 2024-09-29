package com.test.cnss.myProject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.cnss.myProject.model.PaymentMethod;
import com.test.cnss.myProject.repository.PaymentMethodRepository;
import com.test.cnss.myProject.service.PaymentMethodService;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    
    @Override
    public PaymentMethod addPaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }
    
    @Override
    public PaymentMethod getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id).orElse(null);
    }
    
    @Override
    public PaymentMethod updatePaymentMethod(Long id, PaymentMethod paymentMethod) {
        PaymentMethod existingPaymentMethod = paymentMethodRepository.findById(id).orElse(null);
        if (existingPaymentMethod != null) {
            existingPaymentMethod.setType(paymentMethod.getType());
            existingPaymentMethod.setStatus(paymentMethod.getStatus());
            return paymentMethodRepository.save(existingPaymentMethod);
        }
        return null;
        
    }
    
    @Override
    public void deactivatePaymentMethod(Long id) {
        PaymentMethod paymentMethod = getPaymentMethodById(id);
        paymentMethod.setStatus("INACTIVE");
        paymentMethodRepository.save(paymentMethod);
    }
    
    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }
}