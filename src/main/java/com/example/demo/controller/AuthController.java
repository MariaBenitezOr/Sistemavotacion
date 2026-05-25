package com.example.demo.controller;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }



//    @PostMapping("/login")
//    public String login() {
//        return "Login de usuario";
//    }
//
//    @PostMapping("/refresh")
//    public String refresh() {
//        return "Renovar access token";
//    }
//
//    @PostMapping("/logout")
//    public String logout() {
//        return "Logout usuario";
//    }
}