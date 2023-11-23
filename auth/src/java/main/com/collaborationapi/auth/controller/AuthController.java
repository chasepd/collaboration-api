package com.collaborationapi.auth.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.collaborationapi.auth.controller.ApiResponse;
import com.collaborationapi.auth.utils.PasswordUtils;


@RestController
public class AuthController {


    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String email = request.get("email");
        
        String pass_hash = PasswordUtils.hashPassword(password, PasswordUtils.generateSalt());
        
        
        ApiResponse response = new ApiResponse("success");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse> loginUser() {
        // TODO: Implement login logic
        ApiResponse response = new ApiResponse("success");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<String> logoutUser() {
        // TODO: Implement logout logic
        ApiResponse response = new ApiResponse("success");
        return ResponseEntity.ok(response);
    }
}

