package com.test.cnss.myProject.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.cnss.myProject.service.PaymentService;

@RestController
@RequestMapping("/batch/payment")
public class BatchPaymentController {
    
    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("/{customerId}/batch-process")
    public ResponseEntity<Map<String, Object>> batchProcessPayments(@PathVariable Long customerId) {
        Map<String, Object> result = paymentService.processBatchPayments(customerId);
        
        if (result.get("p_error_code") != null) {
            return ResponseEntity.badRequest().body(result);
        }
        
        return ResponseEntity.ok(result);
    }
}