package com.test.cnss.myProject.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.cnss.myProject.model.AuditLog;
import com.test.cnss.myProject.service.AuditLogService;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {
    
    @Autowired
    private AuditLogService auditLogService;
    
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
    @GetMapping
    public ResponseEntity<List<AuditLog>> getAuditLogs(@RequestParam(required = false) String entityType,
            @RequestParam(required = false) String entityId, @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) throws ParseException {
        
        List<AuditLog> auditLogs = auditLogService.getAllAuditLogs(entityType, entityId, userId, formatter.parse(startDate),
                formatter.parse(endDate));
        
        return ResponseEntity.ok(auditLogs);
    }
}