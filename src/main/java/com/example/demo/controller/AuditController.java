package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/audit")

public class AuditController {

    @PostMapping("/verify")
    public String verificarAuditoria(){
        return "Verificar firma HMAC";

    }
    @GetMapping("/Logs")
    public String LogsAuditoria(){
        return "Logs generales de auditoria";
    }
}
