package com.collaborationapi.auth.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntitlementsResponseTest {

    @Test
    public void testEntitlementsResponseConstructor() {
        EntitlementsResponse response = new EntitlementsResponse(true, true, true, true, true, true);
        assertNotNull(response);
        assertTrue(response.getActive());
        assertTrue(response.getAdmin());
        assertTrue(response.getProjectManagement());
        assertTrue(response.getTaskManagement());
        assertTrue(response.getTaskReview());
        assertTrue(response.getNlp());
    }

    @Test
    public void testGetActive() {
        EntitlementsResponse response = new EntitlementsResponse(true, false, false, false, false, false);
        assertTrue(response.getActive());
    }

    @Test
    public void testSetActive() {
        EntitlementsResponse response = new EntitlementsResponse(false, false, false, false, false, false);
        response.setActive(true);
        assertTrue(response.getActive());
    }

    @Test
    public void testGetAdmin() {
        EntitlementsResponse response = new EntitlementsResponse(false, true, false, false, false, false);
        assertTrue(response.getAdmin());
    }

    @Test
    public void testSetAdmin() {
        EntitlementsResponse response = new EntitlementsResponse(false, false, false, false, false, false);
        response.setAdmin(true);
        assertTrue(response.getAdmin());
    }

    @Test
    public void testGetProjectManagement() {
        EntitlementsResponse response = new EntitlementsResponse(false, false, true, false, false, false);
        assertTrue(response.getProjectManagement());
    }

    @Test
    public void testSetProjectManagement() {
        EntitlementsResponse response = new EntitlementsResponse(false, false, false, false, false, false);
        response.setProjectManagement(true);
        assertTrue(response.getProjectManagement());
    }

    @Test
    public void testGetTaskManagement() {
        EntitlementsResponse response = new EntitlementsResponse(false, false, false, true, false, false);
        assertTrue(response.getTaskManagement());
    }

    @Test
    public void testSetTaskManagement() {
        EntitlementsResponse response = new EntitlementsResponse(false, false, false, false, false, false);
        response.setTaskManagement(true);
        assertTrue(response.getTaskManagement());
    }

    @Test
    public void testGetTaskReview() {
        EntitlementsResponse response = new EntitlementsResponse(false, false, false, false, true, false);
        assertTrue(response.getTaskReview());
    }

    @Test
    public void testSetTaskReview() {
        EntitlementsResponse response = new EntitlementsResponse(false, false, false, false, false, false);
        response.setTaskReview(true);
        assertTrue(response.getTaskReview());
    }

    @Test
    public void testGetNlp() {
        EntitlementsResponse response = new EntitlementsResponse(false, false, false, false, false, true);
        assertTrue(response.getNlp());
    }

    @Test
    public void testSetNlp() {
        EntitlementsResponse response = new EntitlementsResponse(false, false, false, false, false, false);
        response.setNlp(true);
        assertTrue(response.getNlp());
    }

}