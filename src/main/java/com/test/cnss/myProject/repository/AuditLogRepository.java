package com.test.cnss.myProject.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.cnss.myProject.model.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByEntityType(String entityType);
    
    List<AuditLog> findByEntityTypeAndEntityIdAndUserIdAndEventDateBetween(String entityType, String entityId, Long userId, Date startDate,
            Date endDate);
}