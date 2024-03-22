package com.collaborationapi.auth.model;

import javax.persistence.*;

@Entity
@Table(name = "entitlements")
public class Entitlements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long userId;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean admin;

    @Column(nullable = false)
    private boolean projectManagement;

    @Column(nullable = false)
    private boolean taskManagement;

    @Column(nullable = false)
    private boolean taskReview;

    @Column(nullable = false)
    private boolean nlp;

    public Entitlements() {
        // This constructor is required by Hibernate
    }

    public Entitlements(Long userId, boolean active, boolean admin, boolean projectManagement, boolean taskManagement, boolean taskReview, boolean nlp) {
        this.userId = userId;
        this.active = active;
        this.admin = admin;
        this.projectManagement = projectManagement;
        this.taskManagement = taskManagement;
        this.taskReview = taskReview;
        this.nlp = nlp;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean getActive() {
        return active;
    }

    public boolean getAdmin() {
        return admin;
    }

    public boolean getProjectManagement() {
        return projectManagement;
    }

    public boolean getTaskManagement() {
        return taskManagement;
    }

    public boolean getTaskReview() {
        return taskReview;
    }

    public boolean getNlp() {
        return nlp;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setProjectManagement(boolean projectManagement) {
        this.projectManagement = projectManagement;
    }

    public void setTaskManagement(boolean taskManagement) {
        this.taskManagement = taskManagement;
    }

    public void setTaskReview(boolean taskReview) {
        this.taskReview = taskReview;
    }

    public void setNlp(boolean nlp) {
        this.nlp = nlp;
    }
}