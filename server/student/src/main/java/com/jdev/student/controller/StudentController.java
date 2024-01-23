package com.jdev.student.controller;

import com.jdev.student.model.DTO.StudentRegistrationDTO;
import com.jdev.student.model.Student;
import com.jdev.student.service.StudentService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService StudentService;

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(StudentService.findAllStudents());
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody @Valid StudentRegistrationDTO student) {
        return ResponseEntity.ok().body(StudentService.create(student));
    }
}
