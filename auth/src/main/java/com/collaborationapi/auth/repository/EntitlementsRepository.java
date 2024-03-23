package com.collaborationapi.auth.repository;

import com.collaborationapi.auth.model.Entitlements;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntitlementsRepository extends JpaRepository<Entitlements, Long> {
    Optional<Entitlements> findByUserId(Long userId);
}
