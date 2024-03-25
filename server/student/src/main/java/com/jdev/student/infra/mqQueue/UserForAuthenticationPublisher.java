package com.jdev.student.infra.mqQueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdev.student.model.DTO.UserForAuthenticationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserForAuthenticationPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueUserForAuthentication;

    public void sendUserForCreateAuthentication(UserForAuthenticationDTO data) throws JsonProcessingException {
        var json = this.convertIntoJson(data);
        rabbitTemplate.convertAndSend(queueUserForAuthentication.getName(), json);
    }

    private String convertIntoJson(UserForAuthenticationDTO data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }
}
