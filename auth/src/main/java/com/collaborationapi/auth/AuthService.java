package com.collaborationapi.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class AuthService {

    public static void main(String[] args) {
        SpringApplication.run(AuthService.class, args);
    }
}
