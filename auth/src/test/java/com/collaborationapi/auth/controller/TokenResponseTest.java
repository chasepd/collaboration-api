package com.collaborationapi.auth.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenResponseTest {

    @Test
    public void testGetMessage() {
        String message = "Test Message";
        String token = "Test Token";
        TokenResponse response = new TokenResponse(message, token);
        assertEquals(message, response.getMessage());
    }

    @Test
    public void testSetMessage() {
        String message = "Test Message";
        String token = "Test Token";
        TokenResponse response = new TokenResponse("", token);
        response.setMessage(message);
        assertEquals(message, response.getMessage());
    }

    @Test
    public void testGetToken() {
        String message = "Test Message";
        String token = "Test Token";
        TokenResponse response = new TokenResponse(message, token);
        assertEquals(token, response.getToken());
    }

    @Test
    public void testSetToken() {
        String message = "Test Message";
        String token = "Test Token";
        TokenResponse response = new TokenResponse(message, "");
        response.setToken(token);
        assertEquals(token, response.getToken());
    }
}