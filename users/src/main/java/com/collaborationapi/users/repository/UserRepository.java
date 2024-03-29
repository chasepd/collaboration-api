package com.collaborationapi.users.repository;

import com.collaborationapi.users.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface UserRepository extends JpaRepository<User, Long> {
    @NonNull List<User> findAll();
}
