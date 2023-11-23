package com.collaborationapi.auth.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.collaborationapi.auth.controller.ApiResponse;
import com.collaborationapi.auth.utils.PasswordUtils;
import java.util.Map;


@RestController
public class AuthController {

    @GetMapping("/auth/health")
    public ResponseEntity<ApiResponse> healthCheck() {
        ApiResponse response = new ApiResponse("ok");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String email = request.get("email");
        try {
            String pass_hash = PasswordUtils.hashPassword(password);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse( "error");
            System.out.println(e);
            return ResponseEntity.ok(response);
        }

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
    public ResponseEntity<ApiResponse> logoutUser() {
        // TODO: Implement logout logic
        ApiResponse response = new ApiResponse("success");
        return ResponseEntity.ok(response);
    }
}

