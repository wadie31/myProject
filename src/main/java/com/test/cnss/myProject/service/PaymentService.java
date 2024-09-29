package com.test.cnss.myProject.service;

import java.util.Map;

public interface PaymentService {
    
    public Map<String, Object> processBatchPayments(Long customerId);
}
