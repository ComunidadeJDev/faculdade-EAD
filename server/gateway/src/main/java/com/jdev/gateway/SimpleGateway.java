package com.jdev.gateway;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.web.servlet.function.RouterFunctions;

import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;


@Configuration
public class SimpleGateway {
    @Bean
    public RouterFunction<ServerResponse> getRoute() {
        return GatewayRouterFunctions.route().GET("/student/**", http("lb:/msstudent")).build();
    }
}
