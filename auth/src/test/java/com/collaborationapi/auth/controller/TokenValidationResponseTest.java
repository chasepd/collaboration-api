package com.collaborationapi.auth.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenValidationResponseTest {

    @Test
    public void testTokenValidationResponse() {
        boolean valid = true;
        String message = "Token is valid";
        String username = "john_doe";
        String email = "john.doe@example.com";
        Long userId = 12345L;

        TokenValidationResponse response = new TokenValidationResponse(valid, message, username, email, userId);

        assertEquals(valid, response.getValid());
        assertEquals(message, response.getMessage());
        assertEquals(username, response.getUsername());
        assertEquals(email, response.getEmail());
        assertEquals(userId, response.getUserId());
    }

    @Test
    public void testSettersAndGetters() {
        TokenValidationResponse response = new TokenValidationResponse(false, "", "", "", 0L);

        response.setValid(true);
        assertTrue(response.getValid());

        response.setMessage("Token is valid");
        assertEquals("Token is valid", response.getMessage());

        response.setUsername("john_doe");
        assertEquals("john_doe", response.getUsername());

        response.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", response.getEmail());

        response.setUserId(12345L);
        assertEquals(12345L, response.getUserId());
    }
}