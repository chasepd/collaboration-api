package com.collaborationapi.auth.controller;

import com.collaborationapi.auth.service.EntitlementsService;
import com.collaborationapi.auth.model.Entitlements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthZControllerTest {

    @Mock
    private EntitlementsService entitlementsService;

    private AuthZController authZController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        authZController = new AuthZController(entitlementsService);
    }

    @SuppressWarnings("null")
    @Test
    public void testGetUserEntitlements() {
        Long userId = 1L;
        Entitlements entitlements = new Entitlements(userId, true, true, true, true, true, true);
        when(entitlementsService.getUserEntitlements(userId)).thenReturn(entitlements);

        ResponseEntity<EntitlementsResponse> response = authZController.getUserEntitlements(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entitlements.getActive(), response.getBody().getActive());
        assertEquals(entitlements.getAdmin(), response.getBody().getAdmin());
        assertEquals(entitlements.getProjectManagement(), response.getBody().getProjectManagement());
        assertEquals(entitlements.getTaskManagement(), response.getBody().getTaskManagement());
        assertEquals(entitlements.getTaskReview(), response.getBody().getTaskReview());
        assertEquals(entitlements.getNlp(), response.getBody().getNlp());
        verify(entitlementsService, times(1)).getUserEntitlements(userId);
    }
}