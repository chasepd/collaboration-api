package com.collaborationapi.auth.repository;

import com.collaborationapi.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
