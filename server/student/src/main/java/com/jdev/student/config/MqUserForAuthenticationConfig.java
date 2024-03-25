package com.jdev.student.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqUserForAuthenticationConfig {

    @Value("${mq.queue.user-for-authentication}")
    private String userForAuthenticationQueue;

    @Bean
    public Queue queueUserForAuthentication() {
        return new Queue(userForAuthenticationQueue, true);
    }
}
