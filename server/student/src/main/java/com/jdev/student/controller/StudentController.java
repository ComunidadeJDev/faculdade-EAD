package com.jdev.student.controller;

import com.jdev.student.model.DTO.StudentRegistrationDTO;
import com.jdev.student.model.DTO.StudentUpdateDTO;
import com.jdev.student.model.Student;
import com.jdev.student.model.enums.FilesType;
import com.jdev.student.service.FilesByStudentsService;
import com.jdev.student.service.ImagesByStudentsService;
import com.jdev.student.service.StudentService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private FilesByStudentsService filesByStudentsService;

    @Autowired
    private ImagesByStudentsService imagesByStudentsService;

    //admin
    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAllStudents());
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody @Valid StudentRegistrationDTO student) {
        return ResponseEntity.ok().body(studentService.create(student));
    }

    //admin
    @GetMapping("/search/registration/{registration}")
    public ResponseEntity<Student> findByRegistration(@PathVariable String registration) throws RuntimeException {
        return ResponseEntity.ok().body(studentService.findByRegistration(registration));
    }

    //admin
    @GetMapping("/search/id/{id}")
    public ResponseEntity<Student> findById(@PathVariable UUID id) throws RuntimeException {
        return ResponseEntity.ok().body(studentService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody StudentUpdateDTO studentUpdate) {
        return ResponseEntity.ok().body(studentService.updateStudent(studentUpdate));
    }
 
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable UUID id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
  
    @PostMapping("/upload/file")
    public ResponseEntity<Object> uploadFile(@RequestParam("file")MultipartFile file,
                                             String username,
                                             FilesType fileType) {
        filesByStudentsService.saveFile(file, username, fileType);
        return ResponseEntity.ok().body("file saved!");
    }

    @PostMapping("/upload/image")
    public ResponseEntity<Object> uploadImage(@RequestParam("image") MultipartFile image, String username) {
        imagesByStudentsService.saveImage(image, username);
        return ResponseEntity.ok().body("image saved!");
    }
}
