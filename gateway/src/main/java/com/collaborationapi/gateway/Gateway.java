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
                        .uri("http://auth:8080/"))
                .route("auth_login", r -> r.path("/auth/login")
                        .and().method(HttpMethod.POST)
                        .uri("http://auth:8080/"))
                .route("auth_logout", r -> r.path("/auth/logout")
                        .and().method(HttpMethod.GET)
                        .uri("http://auth:8080/"))
                .route("users_userslist", r -> r.path("/users")
                        .and().method(HttpMethod.GET)
                        .uri("http://users:8080/"))
                // .route("users_specific_get", r -> r.path("/users/{userId}")
                //         .and().method(HttpMethod.GET)
                //         .uri("http://users:8080/"))
                // .route("users_specific_update", r -> r.path("/users/{userId}")
                //         .and().method(HttpMethod.PUT)
                //         .uri("http://users:8080/"))
                // .route("users_specific_delete", r -> r.path("/users/{userId}")
                //         .and().method(HttpMethod.DELETE)
                //         .uri("http://users:8080/"))
                // .route("project_create", r -> r.path("/projects")
                //         .and().method(HttpMethod.POST)
                //         .uri("http://projects:8080/"))
                // .route("projects_list", r -> r.path("/projects")
                //         .and().method(HttpMethod.GET)
                //         .uri("http://projects:8080/"))
                // .route("project_details", r -> r.path("/projects/{projectId}")
                //         .and().method(HttpMethod.GET)
                //         .uri("http://projects:8080/"))
                // .route("project_update", r -> r.path("/projects/{projectId}")
                //         .and().method(HttpMethod.PUT)
                //         .uri("http://projects:8080/"))
                // .route("project_delete", r -> r.path("/projects/{projectId}")
                //         .and().method(HttpMethod.DELETE)
                //         .uri("http://projects:8080/"))
                // .route("task_create", r -> r.path("/projects/{projectId}/tasks")
                //         .and().method(HttpMethod.POST)
                //         .uri("http://tasks:8080/"))
                // .route("tasks_list", r -> r.path("/projects/{projectId}/tasks")
                //         .and().method(HttpMethod.GET)
                //         .uri("http://tasks:8080/"))
                // .route("task_details", r -> r.path("/projects/{projectId}/tasks/{taskId}")
                //         .and().method(HttpMethod.GET)
                //         .uri("http://tasks:8080/"))
                // .route("task_update", r -> r.path("/projects/{projectId}/tasks/{taskId}")
                //         .and().method(HttpMethod.PUT)
                //         .uri("http://tasks:8080/"))
                // .route("task_delete", r -> r.path("/projects/{projectId}/tasks/{taskId}")
                //         .and().method(HttpMethod.DELETE)
                //         .uri("http://tasks:8080/"))
                .build();
    }
}
