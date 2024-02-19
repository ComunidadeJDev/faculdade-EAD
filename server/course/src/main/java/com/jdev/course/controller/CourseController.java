package com.jdev.course.controller;

import com.jdev.course.model.Course;
import com.jdev.course.model.DTO.CourseCreateDTO;
import com.jdev.course.model.DTO.CourseUpdateDTO;
import com.jdev.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String test() {
        return "oi";
    }

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody CourseCreateDTO courseDTO) {
        return ResponseEntity.ok().body(courseService.create(courseDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Course>> findAllCourses() {
        return ResponseEntity.ok().body(courseService.findAll());
    }

    @GetMapping("/list/{registration}")
    public ResponseEntity<Course> findCourseByRegistration(@PathVariable String registration) {
        return ResponseEntity.ok().body(courseService.findByCourseWithRegistration(registration));
    }

    @PutMapping("/update")
    public ResponseEntity<Course> updateCourse(@RequestBody CourseUpdateDTO updateDTO) {
        return ResponseEntity.ok().body(courseService.update(updateDTO));
    }

    @DeleteMapping("/delete/{registration}")
    public ResponseEntity<Object> deleteCourse(@PathVariable String registration) {
        courseService.setWithNotActive(registration);
        return ResponseEntity.noContent().build();
    }
}
