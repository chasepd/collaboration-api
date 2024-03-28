package com.collaborationapi.auth.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import com.collaborationapi.auth.utils.PasswordUtils;
import com.collaborationapi.auth.model.Entitlements;
import com.collaborationapi.auth.model.User;
import com.collaborationapi.auth.service.UserService;
import com.collaborationapi.auth.service.EntitlementsService;
import java.util.Map;

import com.collaborationapi.auth.utils.JwtUtil;


@RestController
public class AuthNController {

    private final UserService userService;
    private final EntitlementsService entitlementsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthNController(UserService userService, EntitlementsService entitlementsService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.entitlementsService = entitlementsService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/auth/health")
    public ResponseEntity<MessageResponse> healthCheck() {
        MessageResponse response = new MessageResponse("ok");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");
        String email = requestBody.get("email");
        System.out.println("Attempting to register user: " + username);
        User user = userService.findUserByUsername(username);
        if (user != null) {
            System.out.println("User already exists: " + username);
            MessageResponse response = new MessageResponse("error");
            return ResponseEntity.ok(response);
        }
        try {
            System.out.println("User does not exist, creating user: " + username);
            String pass_hash = PasswordUtils.hashPassword(password);
            user = new User(username, pass_hash, email);
            userService.saveUser(user);
            Entitlements entitlements = new Entitlements(user.getId(), true, false, false, false, false, false);
            entitlementsService.saveEntitlements(entitlements);
            System.out.println("Issuing JWT for user: " + username);
            // Generate a JWT for the user
            String token = jwtUtil.generateToken(username);
            System.out.println("User registered successfully: " + username);
            return ResponseEntity.ok(new TokenResponse("success", token));
        } catch (Exception e) {
            MessageResponse response = new MessageResponse("error");
            System.out.println(e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");
        try {
            User user = userService.findUserByUsername(username);
            if (user == null || !PasswordUtils.verifyPassword(password, user.getPasswordHash())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("error"));
            }

            // Generate a JWT for the user
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(new TokenResponse("success", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("error"));
        }
    }

    // Invalidate JWT token
    @GetMapping("/auth/logout")
    public ResponseEntity<MessageResponse> logoutUser() {
        MessageResponse response = new MessageResponse("success");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/auth/validateToken")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        try {
            boolean isValid = jwtUtil.validateToken(token);
            if (isValid) {
                String username = jwtUtil.extractUsername(token);
                User user = userService.findUserByUsername(username);
                // Return a response indicating the token is valid and include user details
                return ResponseEntity.ok(new TokenValidationResponse(true, "Token is valid.", user.getUsername(), user.getEmail(), user.getId()));
            }
        } catch (Exception e) {
            // Log exception and return an appropriate response
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenValidationResponse(false, "Token is invalid.", null, null, null));
    }

}

