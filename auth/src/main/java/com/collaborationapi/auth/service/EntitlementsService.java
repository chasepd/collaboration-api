package com.collaborationapi.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.collaborationapi.auth.model.Entitlements;
import com.collaborationapi.auth.repository.EntitlementsRepository;

@Service
public class EntitlementsService {

    @Autowired
    EntitlementsRepository entitlementsRepository;

    public Entitlements getUserEntitlements(Long userId) {
        return entitlementsRepository.findByUserId(userId).orElse(null);
    }

    public void saveEntitlements(@NonNull Entitlements entitlements) {
        entitlementsRepository.save(entitlements);
    }
}
