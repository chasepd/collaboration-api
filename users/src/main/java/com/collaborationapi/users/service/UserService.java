package com.collaborationapi.users.service;

import com.collaborationapi.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.lang.NonNull;

import com.collaborationapi.users.model.User;
import org.springframework.retry.annotation.Backoff;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Retryable(value = TransientDataAccessException.class, maxAttempts = 5, backoff = @Backoff(delay = 5000))
    public @NonNull Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }
}
