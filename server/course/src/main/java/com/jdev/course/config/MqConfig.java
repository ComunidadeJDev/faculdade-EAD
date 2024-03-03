package com.jdev.course.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    @Value("${mq.queue.courses-for-student}")
    private String coursesForStudentQueue;

    @Bean
    public Queue queueCoursesForStudent() {
        return new Queue(coursesForStudentQueue, true);
    }
}
