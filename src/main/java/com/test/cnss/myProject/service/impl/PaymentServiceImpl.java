package com.test.cnss.myProject.service.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.test.cnss.myProject.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public Map<String, Object> processBatchPayments(Long customerId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PROCESS_BATCH_PAYMENTS").declareParameters(
                new SqlParameter("p_customer_id", Types.OTHER), new SqlOutParameter("p_transaction_count", Types.INTEGER),
                new SqlOutParameter("p_total_amount", Types.DECIMAL), new SqlOutParameter("p_error_code", Types.VARCHAR),
                new SqlOutParameter("p_error_message", Types.VARCHAR));
        
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_customer_id", customerId);
        
        return jdbcCall.execute(inParams);
    }
    
}
