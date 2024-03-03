package com.jdev.course.infra.mqQueues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdev.course.model.DTO.DataCourseForSendToTheStudentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestCoursesForStudentPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queueCoursesForStudent;

    public void sendCourse(DataCourseForSendToTheStudentDTO data) throws JsonProcessingException {
       var json = this.convertIntoJson(data);
       rabbitTemplate.convertAndSend(queueCoursesForStudent.getName(), json);
    }

    private String convertIntoJson(DataCourseForSendToTheStudentDTO data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }
}
