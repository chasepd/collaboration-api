package com.collaborationapi.auth.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PasswordUtilsTest {

    @Test
    public void testHashPassword() {
        String password = "testpassword";

        String hashedPassword = PasswordUtils.hashPassword(password);

        assertTrue(PasswordUtils.verifyPassword(password, hashedPassword));
    }

    @Test
    public void testVerifyPassword() {
        String password = "testpassword";
        String hashedPassword = PasswordUtils.hashPassword(password);

        assertTrue(PasswordUtils.verifyPassword(password, hashedPassword));
        assertFalse(PasswordUtils.verifyPassword("wrongpassword", hashedPassword));
    }
}