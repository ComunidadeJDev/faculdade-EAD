package com.jdev.student.controller;

import com.jdev.student.model.DTO.TeacherRegistrationDTO;
import com.jdev.student.model.externalClasses.Teacher;
import com.jdev.student.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    //create
    @PostMapping
    public ResponseEntity<Teacher> create(@RequestBody @Valid TeacherRegistrationDTO teacher){
        return ResponseEntity.ok().body(teacherService.createTeacher(teacher));
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> findAllTeachers(){
        return ResponseEntity.ok().body(teacherService.findAllTeachers());
    }

}
