package com.UserService.UserService.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    // Use a base64-encoded secret key
    private final String SECRET_KEY = Base64.getEncoder().encodeToString("aGRhZGtzZmRra3NhJSQjJSNAQEAkc2pza2FqYXNsanF3aD8/Pz8/Pz8/Pz8/Pz8/Pz8/Pz8=".getBytes());

    // Increased expiration time to 1 hour
    private long EXPIRATION_TIME = 60000;

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token, String username) {
        try {
            final String extractedUsername = extractUsername(token);
            boolean isValid = extractedUsername.equals(username) && !isTokenExpired(token);
            logger.info("Token validation for user {}: {}", username, isValid);
            return isValid;
        } catch (ExpiredJwtException e) {
            logger.warn("Token expired for user {}: {}", username, e.getMessage());
            return false;
        } catch (Exception e) {
            logger.error("Token validation failed for user {}: {}", username, e.getMessage());
            return false;
        }
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            logger.error("Could not extract claims from token: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid token");
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            return extractAllClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            // If there's an issue extracting claims, assume the token is invalid
            return true;
        }
    }
}
