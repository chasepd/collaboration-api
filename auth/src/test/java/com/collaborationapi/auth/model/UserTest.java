package com.collaborationapi.auth.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    @Test
    public void testUserConstructor() {
        // Arrange
        String username = "testuser";
        String passwordHash = "testpassword";
        String email = "test@example.com";

        // Act
        User user = new User(username, passwordHash, email);

        // Assert
        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(passwordHash, user.getPasswordHash());
        assertEquals(email, user.getEmail());
        assertEquals("user", user.getRole());
    }

    @Test
    public void testUserSetters() {
        // Arrange
        User user = new User();

        // Act
        user.setId(1L);
        user.setUsername("testuser");
        user.setPasswordHash("testpassword");
        user.setEmail("test@example.com");
        user.setRole("admin");

        // Assert
        assertEquals(1L, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("testpassword", user.getPasswordHash());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("admin", user.getRole());
    }
}