package com.collaborationapi.auth.controller;

public class TokenResponse {
    
    private String message;
    private String token;

    public TokenResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
