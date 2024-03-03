package com.jdev.student.infra.mqQueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdev.student.exceptions.customizeExceptions.ErrorReadResponseOfTheRequestException;
import com.jdev.student.model.externalClasses.Course;
import com.jdev.student.service.externalClasses.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CoursesForStudentSubscriber {

    @Autowired
    private CourseService courseService;

    @RabbitListener(queues = "${mq.queue.courses-for-student}")
    public void responseOfTheRequestCoursesForStudent(@Payload String payload) {
        Course course = this.convertJsonInCourse(payload);
        this.courseService.createCourse(course);
    }

    private Course convertJsonInCourse(String payload) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(payload, Course.class);
        } catch (Exception ex) {
            throw new ErrorReadResponseOfTheRequestException(ex.getMessage());
        }
    }
}
