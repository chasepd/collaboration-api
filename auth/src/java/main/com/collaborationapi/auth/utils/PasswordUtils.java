package com.collaborationapi.auth.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtils {
        public static String generateSalt() {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            return bytesToHex(salt);
        }

        public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
            String saltedPassword = salt + password;
            MessageDigest digest = MessageDigest.getInstance("bcrypt");
            byte[] hashedBytes = digest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashedBytes);
        }

        private static String bytesToHex(byte[] bytes) {
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        }
    }