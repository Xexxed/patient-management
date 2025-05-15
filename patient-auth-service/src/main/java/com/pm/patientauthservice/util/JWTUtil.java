package com.pm.patientauthservice.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTUtil {

    private final Key secretKey;

    public JWTUtil(@Value("${jwt.secret}") String secret) {

        byte[] keyBytes= Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8));
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);


    }
    public String generateToken(String email, String role) {
        // Placeholder for actual token generation logic
        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (3600000*10))) // 10 hour expiration
                .signWith(secretKey)
                .compact();
    }
}
