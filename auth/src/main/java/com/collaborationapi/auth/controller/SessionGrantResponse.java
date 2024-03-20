package com.collaborationapi.auth.controller;

public class SessionGrantResponse {
    private String message;
    private String sessionId;

    public SessionGrantResponse(String message, String sessionToken) {
        this.message = message;
        this.sessionId = sessionToken;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionToken) {
        this.sessionId = sessionToken;
    }
}
