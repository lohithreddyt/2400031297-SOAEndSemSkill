package com.example.tourists.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {
    // Development key only. Override with JWT_SECRET in production.
    private final String secret = System.getenv().getOrDefault(
        "JWT_SECRET", "replace-this-development-secret-key-with-at-least-32-bytes"
    );
    private SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    public String create(String username) {
        Date now = new Date();
        return Jwts.builder().subject(username).issuedAt(now)
            .expiration(new Date(now.getTime() + 60 * 60 * 1000))
            .signWith(key()).compact();
    }
    public Claims inspect(String token) {
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload();
    }
}
