package com.jdev.student.service.externalClasses;

import com.jdev.student.exceptions.customizeExceptions.CourseNotFoundException;
import com.jdev.student.model.externalClasses.Course;
import com.jdev.student.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public void createCourse(Course course) {
        this.courseRepository.save(course);
        log.info("Course created!");
    }

    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    public Course findByRegistration(String registration) {
        Optional<Course> course = this.courseRepository.findByRegistration(registration);
        return course.orElseThrow(CourseNotFoundException::new);
    }
}
