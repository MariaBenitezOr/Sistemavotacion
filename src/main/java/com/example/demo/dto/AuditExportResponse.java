package com.example.demo.dto;

import java.util.List;


public class AuditExportResponse {
    private List<AuditLogResponse> logs;

    private String signature;

    public List<AuditLogResponse> getLogs() {
        return logs;
    }

    public void setLogs(List<AuditLogResponse> logs) {
        this.logs = logs;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
