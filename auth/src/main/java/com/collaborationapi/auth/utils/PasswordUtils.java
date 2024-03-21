package com.collaborationapi.auth.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtils {
         public static String hashPassword(String password) {
            return BCrypt.hashpw(password, BCrypt.gensalt());
        }

        public static boolean verifyPassword(String hash1, String hash2) {
            return BCrypt.checkpw(hash1, hash2);
        }
    }