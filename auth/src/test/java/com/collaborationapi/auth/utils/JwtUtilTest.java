package com.collaborationapi.auth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    private JwtUtil jwtUtil;
    private String secret;

    @BeforeEach
    public void setUp() {
        secret = "test-secret";
        jwtUtil = new JwtUtil(secret);
    }

    @Test
    public void testGenerateToken() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);

        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    public void testValidateTokenWithValidToken() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);

        assertTrue(jwtUtil.validateToken(token));
    }

    @Test
    public void testValidateTokenWithExpiredToken() {
        String username = "testuser";
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 10)) // 10 hours ago
                .setExpiration(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 5)) // 5 hours ago
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        try {
            jwtUtil.validateToken(token);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof io.jsonwebtoken.ExpiredJwtException);
        }
    }

    @Test
    public void testExtractUsername() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);

        assertEquals(username, jwtUtil.extractUsername(token));
    }

    @Test
    public void testExtractExpiration() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);

        assertNotNull(jwtUtil.extractExpiration(token));
    }

    @Test
    public void testExtractClaim() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);

        Function<Claims, String> claimsResolver = Claims::getSubject;
        String extractedUsername = jwtUtil.extractClaim(token, claimsResolver);

        assertEquals(username, extractedUsername);
    }
}