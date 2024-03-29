package com.collaborationapi.users.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.collaborationapi.users.model.User;
import com.collaborationapi.users.service.UserEntitlementsService;
import com.collaborationapi.users.service.UserService;

@RestController
public class UsersController {

    private final UserService userService;
    private final UserEntitlementsService userEntitlementsService;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    public UsersController(UserService userService, UserEntitlementsService userEntitlementsService, WebClient.Builder webClientBuilder) {
        this.userService = userService;
        this.userEntitlementsService = userEntitlementsService;
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("/users/health")
    public ResponseEntity<MessageResponse> healthCheck() {
        MessageResponse response = new MessageResponse("ok");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(HttpServletRequest request) {
        String token = extractToken(request);
        System.out.println("Received token, will validate: " + token);
        TokenValidationResponse tokenValidationResponse = validateToken(token);
        if (token == null || !tokenValidationResponse.getValid()) {
            // Token is invalid or not provided
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: Token is invalid or not provided.");
        }
        System.out.println("Token is valid. Getting user entitlements.");
        Long userId = tokenValidationResponse.getUserId();

        EntitlementsResponse entitlementsResponse = getUserEntitlements(userId);

        if (entitlementsResponse == null || !entitlementsResponse.getActive()) {
            // User is not active
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Forbidden: User is not active.");
        }

        if (!entitlementsResponse.getAdmin()) {
            // User is not an admin
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Forbidden: User is not an admin.");
        }

        Iterable<User> users = userService.findAllUsers();
        return ResponseEntity.ok(new ListResponse("success", users));
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private TokenValidationResponse validateToken(String token) {
        try {
            WebClient webClient = webClientBuilder.baseUrl("http://auth:8080").build();
            TokenValidationResponse response = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/auth/validateToken")
                    .queryParam("token", token)
                    .build())
                    .retrieve()
                    .bodyToMono(TokenValidationResponse.class)
                    .block();
    
            return response;
        } catch (Exception e) {
            System.out.println(e);            
            return null;
        }
    }

    private EntitlementsResponse getUserEntitlements(Long userId) {
        try {
            WebClient webClient = webClientBuilder.baseUrl("http://auth:8080").build();
            EntitlementsResponse response = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/auth/user_entitlements")
                    .queryParam("userId", userId)
                    .build())
                    .retrieve()
                    .bodyToMono(EntitlementsResponse.class)
                    .block();

            return response;
        } catch (Exception e) {  
            System.out.println(e);          
            return null;
        }
    }

}