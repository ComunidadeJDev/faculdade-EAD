package com.jdev.student.controller;

import com.jdev.student.model.FilesAndImages.ImagesByStudents;
import com.jdev.student.service.ImagesByStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("images")
public class ImagesByStudentsController {

    @Autowired
    private ImagesByStudentsService imagesByStudentsService;

    @GetMapping
    public ResponseEntity<List<ImagesByStudents>> findAll() {
        return ResponseEntity.ok().body(imagesByStudentsService.findAll());
    }
}
