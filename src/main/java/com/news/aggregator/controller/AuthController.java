package com.news.aggregator.controller;

import com.news.aggregator.dto.AuthResponse;
import com.news.aggregator.dto.LoginRequest;
import com.news.aggregator.dto.RegisterRequest;
import com.news.aggregator.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
