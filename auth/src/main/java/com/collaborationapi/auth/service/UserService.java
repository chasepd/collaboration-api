package com.collaborationapi.auth.service;

import com.collaborationapi.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.dao.TransientDataAccessException;
import com.collaborationapi.auth.model.User;
import org.springframework.retry.annotation.Backoff;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Retryable(value = TransientDataAccessException.class, maxAttempts = 5, backoff = @Backoff(delay = 5000))
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Retryable(value = TransientDataAccessException.class, maxAttempts = 5, backoff = @Backoff(delay = 5000))
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Retryable(value = TransientDataAccessException.class, maxAttempts = 5, backoff = @Backoff(delay = 5000))
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Retryable(value = TransientDataAccessException.class, maxAttempts = 5, backoff = @Backoff(delay = 5000))
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Retryable(value = TransientDataAccessException.class, maxAttempts = 5, backoff = @Backoff(delay = 5000))
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Retryable(value = TransientDataAccessException.class, maxAttempts = 5, backoff = @Backoff(delay = 5000))
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
