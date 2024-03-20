package com.collaborationapi.auth.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionGrantResponseTest {

    @Test
    public void testGetMessage() {
        String message = "Test message";
        String sessionId = "Test session ID";
        SessionGrantResponse response = new SessionGrantResponse(message, sessionId);

        assertEquals(message, response.getMessage());
    }

    @Test
    public void testSetMessage() {
        String message = "Test message";
        String sessionId = "Test session ID";
        SessionGrantResponse response = new SessionGrantResponse("", sessionId);

        response.setMessage(message);

        assertEquals(message, response.getMessage());
    }

    @Test
    public void testGetSessionId() {
        String message = "Test message";
        String sessionId = "Test session ID";
        SessionGrantResponse response = new SessionGrantResponse(message, sessionId);

        assertEquals(sessionId, response.getSessionId());
    }

    @Test
    public void testSetSessionId() {
        String message = "Test message";
        String sessionId = "Test session ID";
        SessionGrantResponse response = new SessionGrantResponse(message, "");

        response.setSessionId(sessionId);

        assertEquals(sessionId, response.getSessionId());
    }
}