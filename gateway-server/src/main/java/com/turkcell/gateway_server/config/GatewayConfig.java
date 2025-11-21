package com.turkcell.gateway_server.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("book-service", r -> r
                        .path("/api/v1/books/**")
                        .filters(f -> f.retry(config -> config.setRetries(3)))
                        .uri("lb://book-service"))
                .route("author-service", r -> r
                        .path("/api/v1/authors/**")
                        .filters(f->f.retry(config -> config.setRetries(3)))
                        .uri("lb://author-service")
                ).route("publisher-service", r-> r
                        .path("/api/v1/publishers/**")
                        .filters(f->f.retry(config -> config.setRetries(3)))
                        .uri("lb://publisher-service"))
                .route("member-service", r-> r
                        .path("/api/v1/members/**")
                        .filters(f->f.retry(config -> config.setRetries(3)))
                        .uri("lb://member-service"))
                .route("loan-service", r-> r
                        .path("/api/v1/loans/**")
                        .filters(f->f.retry(config -> config.setRetries(3)))
                        .uri("lb://loan-service"))
                .route("staff-service", r-> r
                        .path("/api/v1/staffs/**")
                        .filters(f->f.retry(config -> config.setRetries(3)))
                        .uri("lb://staff-service"))
                .route("reservation-service", r-> r
                        .path("/api/v1/reservations/**")
                        .filters(f->f.retry(config -> config.setRetries(3)))
                        .uri("lb://reservation-service"))
                .route(
                        "bff-service", r-> r
                                .path("/api/**")
                                .filters(f-> f
                                        .removeRequestHeader("Cookie")
                                        .rewritePath("/api/(?<segment>.*)", "/${segment}")
                                        .tokenRelay())
                                .uri("lb://bff-service")
                )

                .route("fallback", r -> r
                        .path("/**")
                        .filters(f -> f.setStatus(HttpStatus.NOT_FOUND))
                        .uri("no://op"))
                .build();
    }
}
