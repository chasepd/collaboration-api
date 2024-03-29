package com.collaborationapi.users.controller;

public class ListResponse {
    private String message;
    Iterable<?> list;

    public ListResponse(String message, Iterable<?> list) {
        this.message = message;
        this.list = list;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Iterable<?> getList() {
        return list;
    }

    public void setList(Iterable<?> list) {
        this.list = list;
    }
}
