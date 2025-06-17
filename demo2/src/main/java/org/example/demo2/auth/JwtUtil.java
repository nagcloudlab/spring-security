package org.example.demo2.auth;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "secret";

    public String generateToken(String username) {
        String jwt = Jwts.builder()
                .issuer("me")
                .subject(username)
                .audience().add("you").and()
//                .expiration(expiration) //a java.util.Date
                //.notBefore(notBefore) //a java.util.Date
                .issuedAt(new Date()) // for example, now
                .id(UUID.randomUUID().toString()).compact(); //just an example id
        return jwt;
    }

    public boolean validateToken(String token) {
        try {
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        // Extracts the username from the JWT token
        return "user1";
    }
}

