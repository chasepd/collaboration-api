package com.collaborationapi.users.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class UserEntitlementsService {
    
    private final RestTemplate restTemplate;

    public UserEntitlementsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean hasAdminEntitlement(String userId) {
        ResponseEntity<Map<String, Boolean>> response = restTemplate.exchange(
            "http://auth/auth/user_entitlements?userId={userId}",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Map<String, Boolean>>() {},
            userId
        );
    
        Map<String, Boolean> entitlementsResponse = response.getBody();
        return entitlementsResponse != null && Boolean.TRUE.equals(entitlementsResponse.get("admin"));
    }
    
}
