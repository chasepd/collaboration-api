package com.collaborationapi.auth.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntitlementsTest {

    @Test
    public void testEntitlementsConstructor() {
        Long userId = 1L;
        boolean active = true;
        boolean admin = true;
        boolean projectManagement = true;
        boolean taskManagement = true;
        boolean taskReview = true;
        boolean nlp = true;

        Entitlements entitlements = new Entitlements(userId, active, admin, projectManagement, taskManagement, taskReview, nlp);

        assertNotNull(entitlements);
        assertEquals(userId, entitlements.getUserId());
        assertEquals(active, entitlements.getActive());
        assertEquals(admin, entitlements.getAdmin());
        assertEquals(projectManagement, entitlements.getProjectManagement());
        assertEquals(taskManagement, entitlements.getTaskManagement());
        assertEquals(taskReview, entitlements.getTaskReview());
        assertEquals(nlp, entitlements.getNlp());
    }

    @Test
    public void testEntitlementsGettersAndSetters() {
        Long userId = 1L;
        boolean active = true;
        boolean admin = true;
        boolean projectManagement = true;
        boolean taskManagement = true;
        boolean taskReview = true;
        boolean nlp = true;

        Entitlements entitlements = new Entitlements();
        entitlements.setUserId(userId);
        entitlements.setActive(active);
        entitlements.setAdmin(admin);
        entitlements.setProjectManagement(projectManagement);
        entitlements.setTaskManagement(taskManagement);
        entitlements.setTaskReview(taskReview);
        entitlements.setNlp(nlp);

        assertEquals(userId, entitlements.getUserId());
        assertEquals(active, entitlements.getActive());
        assertEquals(admin, entitlements.getAdmin());
        assertEquals(projectManagement, entitlements.getProjectManagement());
        assertEquals(taskManagement, entitlements.getTaskManagement());
        assertEquals(taskReview, entitlements.getTaskReview());
        assertEquals(nlp, entitlements.getNlp());
    }
}