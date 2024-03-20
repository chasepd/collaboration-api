package com.collaborationapi.auth.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtils {
         public static String hashPassword(String password) {
            return BCrypt.hashpw(password, BCrypt.gensalt());
        }

        private static String bytesToHex(byte[] bytes) {
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        }

        public static boolean verifyPassword(String hash1, String hash2) {
            return BCrypt.checkpw(hash1, hash2);
        }
    }