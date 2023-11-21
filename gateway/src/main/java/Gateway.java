package com.collaborationapi.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpMethod;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Gateway {

    public static void main(String[] args) {
        SpringApplication.run(Gateway.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth_register", r -> r.path("/auth/register")
                        .and().method(HttpMethod.POST)
                        .uri("http://auth/"))
                .route("auth_login", r -> r.path("/auth/login")
                        .and().method(HttpMethod.POST)
                        .uri("http://auth/"))
                .route("auth_logout", r -> r.path("/auth/logout")
                        .and().method(HttpMethod.POST)
                        .uri("http://auth/"))
                .route("users_userslist", r -> r.path("/users")
                        .and().method(HttpMethod.GET)
                        .uri("http://users/"))
                .route("users_specific_get", r -> r.path("/users/{userId}")
                        .and().method(HttpMethod.GET)
                        .uri("http://users/"))
                .route("users_specific_update", r -> r.path("/users/{userId}")
                        .and().method(HttpMethod.PUT)
                        .uri("http://users/"))
                .route("users_specific_delete", r -> r.path("/users/{userId}")
                        .and().method(HttpMethod.DELETE)
                        .uri("http://users/"))
                .build();
    }
}
