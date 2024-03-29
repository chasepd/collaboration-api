package com.collaborationapi.users.controller;

public class EntitlementsResponse {
    private boolean active;
    private boolean admin;
    private boolean projectManagement;
    private boolean taskManagement;
    private boolean taskReview;
    private boolean nlp;

    public EntitlementsResponse(boolean active, boolean admin, boolean projectManagement, boolean taskManagement, boolean taskReview, boolean nlp) {
        this.active = active;
        this.admin = admin;
        this.projectManagement = projectManagement;
        this.taskManagement = taskManagement;
        this.taskReview = taskReview;
        this.nlp = nlp;
    }

    // Getters and Setters

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean getProjectManagement() {
        return projectManagement;
    }

    public void setProjectManagement(boolean projectManagement) {
        this.projectManagement = projectManagement;
    }

    public boolean getTaskManagement() {
        return taskManagement;
    }

    public void setTaskManagement(boolean taskManagement) {
        this.taskManagement = taskManagement;
    }

    public boolean getTaskReview() {
        return taskReview;
    }

    public void setTaskReview(boolean taskReview) {
        this.taskReview = taskReview;
    }

    public boolean getNlp() {
        return nlp;
    }

    public void setNlp(boolean nlp) {
        this.nlp = nlp;
    }
}
