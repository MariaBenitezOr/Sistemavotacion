package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/public")
    public String publico() {
        return "Endpoint público";
    }

    @GetMapping("/private")
    public String privado(Authentication auth) {
        return "Hola " + auth.getName();
    }
}