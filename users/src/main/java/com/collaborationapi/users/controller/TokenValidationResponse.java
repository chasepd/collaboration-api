package com.collaborationapi.users.controller;

public class TokenValidationResponse {
    
    private boolean valid;
    private String message;
    private String username;
    private String email;
    private Long userId;

    public TokenValidationResponse(boolean valid, String message, String username, String email, Long userId) {
        this.valid = valid;
        this.message = message;
        this.username = username;
        this.email = email;
        this.userId = userId;
    }

    // Getters and Setters

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
