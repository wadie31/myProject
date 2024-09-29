package com.test.cnss.myProject.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.cnss.myProject.model.AuditLog;
import com.test.cnss.myProject.repository.AuditLogRepository;
import com.test.cnss.myProject.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService {
    
    @Autowired
    private AuditLogRepository auditLogRepository;
    
    @Override
    public List<AuditLog> getAllAuditLogs(String entityType, String entityId, Long userId, Date startDate, Date endDate) {
        return auditLogRepository.findByEntityTypeAndEntityIdAndUserIdAndEventDateBetween(entityType, entityId, userId, startDate, endDate);
    }
    
}