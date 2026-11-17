package com.example.tourists.controller;

import com.example.tourists.security.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

@RestController
public class AuthController {
    private final TokenService tokenService;
    public AuthController(TokenService tokenService) { this.tokenService = tokenService; }

    @PostMapping("/token")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        if (!"admin".equals(username) || !"admin123".equals(password))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect username or password");
        return Map.of("access_token", tokenService.create(username), "token_type", "Bearer");
    }

    @GetMapping("/auth/inspect")
    public Map<String, Object> inspect(@RequestHeader("Authorization") String authorization) {
        try {
            Claims claims = tokenService.inspect(authorization.substring(7));
            return Map.of("valid", true, "subject", claims.getSubject(),
                          "expires_at", claims.getExpiration().toString());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token");
        }
    }
}
