package com.collaborationapi.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.collaborationapi.auth.service.EntitlementsService;
import com.collaborationapi.auth.model.Entitlements;

@RestController
public class AuthZController {
    private final EntitlementsService entitlementsService;

    @Autowired
    public AuthZController(EntitlementsService entitlementsService) {
        this.entitlementsService = entitlementsService;
    }

    @GetMapping("/auth/user_entitlements")
    public ResponseEntity<EntitlementsResponse> getUserEntitlements(@RequestParam Long userId) {
        Entitlements entitlements = entitlementsService.getUserEntitlements(userId);
        if (entitlements == null) {
            return ResponseEntity.status(404).body(null);
        }
        EntitlementsResponse response = new EntitlementsResponse(entitlements.getActive(), entitlements.getAdmin(), entitlements.getProjectManagement(), entitlements.getTaskManagement(), entitlements.getTaskReview(), entitlements.getNlp());
        return ResponseEntity.ok(response);
    }
}
