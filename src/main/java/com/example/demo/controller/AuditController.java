package com.example.demo.controller;

import com.example.demo.dto.AuditExportResponse;
import com.example.demo.dto.VerifySignatureRequest;
import com.example.demo.service.HmacService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.AuditService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/audit")

public class AuditController {

    private final AuditService auditService;
    private final HmacService hmacService;

    public AuditController(AuditService auditService,
                           HmacService hmacService) {
        this.auditService = auditService;
        this.hmacService  = hmacService;
    }

    @PreAuthorize("hasRole('AUDITOR')")
    @GetMapping
    public AuditExportResponse getAllLogs (@AuthenticationPrincipal Jwt jwt) {

        String actor = jwt.getClaimAsString("email");
        auditService.log(
                actor,
                "AUDIT_VIEWED",
                "AUDIT",
                null,
                "El auditor consultó los logs"
        );

        return auditService.exportLogs ();
    }

    @PreAuthorize("hasRole('AUDITOR')")
    @PostMapping("/verify")
    public boolean verificarAuditoria(@Valid @RequestBody VerifySignatureRequest request){

        return hmacService.verifySignature(
                request.getData(),
                request.getSignature()
        );
    }

    //SoloParaTest
    @PreAuthorize("hasRole('AUDITOR')")
    @GetMapping("/test-sign")
    public String testSign() {
        return hmacService.generateSignature("hola");
    }

//    @GetMapping("/Logs")
//    public String LogsAuditoria(){
//        return "Logs generales de auditoria";
//    }
}
