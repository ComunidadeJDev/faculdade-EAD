package com.jdev.gateway.Security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private final WebClient webClient;

    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        List<String> excludePaths = List.of("/auth/login", "/auth/register", "/auth/verify");

        if (excludePaths.contains(path)) {
            return chain.filter(exchange); // Pula a autenticação
        }

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (token == null || token.isEmpty()) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return webClient.post().uri("/auth/verify")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response -> {
                    if (response.equals("authenticated")) {
                        return chain.filter(exchange);
                    }
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                });
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
