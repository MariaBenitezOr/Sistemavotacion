package com.example.demo.service;

import com.example.demo.entity.AuditLog;
import com.example.demo.repository.AuditLogRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.example.demo.dto.AuditLogResponse;
import java.util.List;
import java.util.stream.Collectors;
import com.example.demo.dto.AuditExportResponse;

@Service
public class AuditService {

    private final AuditLogRepository auditLogRepository;
    private final HmacService hmacService;

    public AuditService (AuditLogRepository auditLogRepository,
                         HmacService hmacService){
        this.auditLogRepository = auditLogRepository;
        this.hmacService = hmacService;

    }

    public void log(String actor,
                    String action,
                    String entityType,
                    Long entityId,
                    String detail) {

        AuditLog auditLog = new AuditLog();

        auditLog.setActor(actor);
        auditLog.setAction(action);
        auditLog.setEntityType(entityType);
        auditLog.setEntityId(entityId);
        auditLog.setDetail(detail);
        auditLog.setCreatedAt(LocalDateTime.now());

        auditLogRepository.save(auditLog);
    }

    public List<AuditLogResponse> getAllLogs() {

        return auditLogRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private AuditLogResponse mapToResponse(AuditLog auditLog) {

        AuditLogResponse response = new AuditLogResponse();

        response.setId(auditLog.getId());
        response.setActor(auditLog.getActor());
        response.setAction(auditLog.getAction());
        response.setEntityType(auditLog.getEntityType());
        response.setEntityId(auditLog.getEntityId());
        response.setDetail(auditLog.getDetail());
        response.setCreatedAt(auditLog.getCreatedAt());

        return response;
    }

    public AuditExportResponse exportLogs() {

        List<AuditLogResponse> logs = getAllLogs();

        String dataToSign = logs.toString();

        String signature = hmacService.generateSignature(dataToSign);

        AuditExportResponse response = new AuditExportResponse();
        response.setLogs(logs);
        response.setSignature(signature);

        return response;
    }

}
