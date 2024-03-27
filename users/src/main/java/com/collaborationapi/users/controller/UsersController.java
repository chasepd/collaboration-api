package com.collaborationapi.users.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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
    public UsersController(UserService userService, UserEntitlementsService userEntitlementsService) {
        this.userService = userService;
        this.userEntitlementsService = userEntitlementsService;
    }

    @GetMapping("/users/health")
    public ResponseEntity<MessageResponse> healthCheck() {
        MessageResponse response = new MessageResponse("ok");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    public ResponseEntity<ListResponse> getUsers(HttpServletRequest request) {

        // String userId = (String) request.getSession().getAttribute("userId");        

        // if (userId == null) {
        //     return ResponseEntity.status(401).body(new ListResponse("error", null));
        // }

        // boolean isAdmin = false;
        // if (userId != null) {
        //     isAdmin = userEntitlementsService.hasAdminEntitlement(userId);
        // }

        // if (!isAdmin) {
        //     return ResponseEntity.status(403).body(new ListResponse("error", null));
        // }

        Iterable<User> users = userService.findAllUsers();

        return ResponseEntity.ok(new ListResponse("success", users));
    }
    
}