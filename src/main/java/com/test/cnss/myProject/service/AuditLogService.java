package com.test.cnss.myProject.service;

import java.util.Date;
import java.util.List;

import com.test.cnss.myProject.model.AuditLog;

public interface AuditLogService {
    public List<AuditLog> getAllAuditLogs(String entityType, String entityId, Long userId, Date startDate, Date endDate);
}
