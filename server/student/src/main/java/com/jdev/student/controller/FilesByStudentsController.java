package com.jdev.student.controller;

import com.jdev.student.model.Materials.FilesByStudents;
import com.jdev.student.service.FilesByStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("files")
public class FilesByStudentsController {

    @Autowired
    private FilesByStudentsService filesByStudentsService;

    @GetMapping
    public ResponseEntity<List<FilesByStudents>> findAll() {
        return ResponseEntity.ok().body(filesByStudentsService.findAll());
    }
}
