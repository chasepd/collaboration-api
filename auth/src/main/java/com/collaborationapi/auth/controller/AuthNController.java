package com.collaborationapi.auth.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import com.collaborationapi.auth.utils.PasswordUtils;
import com.collaborationapi.auth.model.Entitlements;
import com.collaborationapi.auth.model.User;
import com.collaborationapi.auth.service.UserService;
import com.collaborationapi.auth.service.EntitlementsService;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
public class AuthNController {

    private final UserService userService;
    private final EntitlementsService entitlementsService;

    @Autowired
    public AuthNController(UserService userService, EntitlementsService entitlementsService) {
        this.userService = userService;
        this.entitlementsService = entitlementsService;
    }

    @GetMapping("/auth/health")
    public ResponseEntity<MessageResponse> healthCheck() {
        MessageResponse response = new MessageResponse("ok");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<SessionGrantResponse> registerUser(HttpServletRequest request, @RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");
        String email = requestBody.get("email");
        System.out.println("Attempting to register user: " + username);
        User user = userService.findUserByUsername(username);
        if (user != null) {
            System.out.println("User already exists: " + username);
            SessionGrantResponse response = new SessionGrantResponse("error", null);
            return ResponseEntity.ok(response);
        }
        try {
            System.out.println("User does not exist, creating user: " + username);
            String pass_hash = PasswordUtils.hashPassword(password);
            user = new User(username, pass_hash, email);
            userService.saveUser(user);
            Entitlements entitlements = new Entitlements(user.getId(), true, false, false, false, false, false);
            entitlementsService.saveEntitlements(entitlements);
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("userId", user.getId());
            SessionGrantResponse response = new SessionGrantResponse("success", session.getId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            SessionGrantResponse response = new SessionGrantResponse("error", null);
            System.out.println(e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<SessionGrantResponse> loginUser(HttpServletRequest request, @RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");
        System.out.println("Attempting to login user: " + username);
        try{
            User user = userService.findUserByUsername(username);            
            if (user == null) {
                System.out.println("User not found: " + username);
                SessionGrantResponse response = new SessionGrantResponse("error", null);
                return ResponseEntity.ok(response);
            }
            if (PasswordUtils.verifyPassword(password, user.getPasswordHash())) {
                System.out.println("User found: " + user);
                //Start session
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                SessionGrantResponse response = new SessionGrantResponse("success", session.getId());
                return ResponseEntity.ok(response);
            } else {
                SessionGrantResponse response = new SessionGrantResponse("error", null);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            SessionGrantResponse response = new SessionGrantResponse("error", null);
            System.out.println(e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/auth/logout")
    public ResponseEntity<MessageResponse> logoutUser(HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            session.invalidate();
            MessageResponse response = new MessageResponse("success");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            MessageResponse response = new MessageResponse("error");
            return ResponseEntity.ok(response);
        }
    }
}

