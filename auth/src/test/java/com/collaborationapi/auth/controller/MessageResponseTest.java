package com.collaborationapi.auth.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageResponseTest {

    @Test
    public void testGetMessage() {
        // Create a MessageResponse instance with a message
        String message = "Test message";
        MessageResponse response = new MessageResponse(message);

        // Assert that the getMessage() method returns the correct message
        assertEquals(message, response.getMessage());
    }

    @Test
    public void testSetMessage() {
        // Create a MessageResponse instance
        MessageResponse response = new MessageResponse("");

        // Set a new message
        String message = "New message";
        response.setMessage(message);

        // Assert that the getMessage() method returns the new message
        assertEquals(message, response.getMessage());
    }
}