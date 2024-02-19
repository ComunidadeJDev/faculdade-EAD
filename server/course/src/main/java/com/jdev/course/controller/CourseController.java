package com.jdev.course.controller;

import com.jdev.course.model.Course;
import com.jdev.course.model.DTO.CourseCreateDTO;
import com.jdev.course.model.DTO.CourseUpdateDTO;
import com.jdev.course.model.DTO.ModuleCreateDTO;
import com.jdev.course.model.DTO.ModuleUpdateDTO;
import com.jdev.course.model.Module;
import com.jdev.course.service.CourseService;
import com.jdev.course.service.ModuleService;
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

    @Autowired
    private ModuleService moduleService;

    @GetMapping
    public String test() {
        return "oi";
    }

    //------------------------------------------- Course -------------------------------------------

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

    @DeleteMapping("/disable/{registration}")
    public ResponseEntity<Object> disableCourse(@PathVariable String registration) {
        courseService.setWithNotActive(registration);
        return ResponseEntity.noContent().build();
    }

    //------------------------------------------- Module -------------------------------------------

    @GetMapping("/module/list")
    public ResponseEntity<List<Module>> findAllModules() {
        return ResponseEntity.ok().body(moduleService.findAllModules());
    }

    @PostMapping("/module/create")
    public ResponseEntity<Module> createModule(@RequestBody ModuleCreateDTO moduleDTO) {
        return ResponseEntity.ok().body(moduleService.create(moduleDTO));
    }

    @GetMapping("/module/list/{registration}")
    public ResponseEntity<Module> findModuleByRegistration(@PathVariable String registration) {
        return ResponseEntity.ok().body(moduleService.findByModuleWithRegistration(registration));
    }

    @PutMapping("/module/update")
    public ResponseEntity<Module> updateModule(@RequestBody ModuleUpdateDTO updateDTO) {
        return ResponseEntity.ok().body(moduleService.update(updateDTO));
    }

    @DeleteMapping("/module/disable/{registration}")
    public ResponseEntity<Object> disableModule(@PathVariable String registration) {
        moduleService.setWithNotActive(registration);
        return ResponseEntity.noContent().build();
    }

}
