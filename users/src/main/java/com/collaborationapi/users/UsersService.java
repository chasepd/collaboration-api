package com.collaborationapi.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class UsersService {
    public static void main(String[] args) {
        SpringApplication.run(UsersService.class, args);
    }
}
