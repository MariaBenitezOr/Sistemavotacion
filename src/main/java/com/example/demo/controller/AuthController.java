package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/register")
    public String register() {
        return "Registro de usuario";
    }

    @PostMapping("/login")
    public String login() {
        return "Login de usuario";
    }

    @PostMapping("/refresh")
    public String refresh() {
        return "Renovar access token";
    }

    @PostMapping("/logout")
    public String logout() {
        return "Logout usuario";
    }
}