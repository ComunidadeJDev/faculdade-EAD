package com.jdev.student.controller;

import com.jdev.student.model.DTO.StudentRegistrationDTO;
import com.jdev.student.model.DTO.StudentUpdateDTO;
import com.jdev.student.model.FilesAndImages.FilesByStudents;
import com.jdev.student.model.FilesAndImages.ImagesByStudents;
import com.jdev.student.model.Student;
import com.jdev.student.model.enums.FilesTypeEnum;
import com.jdev.student.service.FilesByStudentsService;
import com.jdev.student.service.ImagesByStudentsService;
import com.jdev.student.service.StudentService;
import jakarta.validation.Valid;
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

    // -------------------------------------------- Student --------------------------------------------
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

    // -------------------------------------------- Files by Student --------------------------------------------

    //ADM
    @GetMapping("/files")
    public ResponseEntity<List<FilesByStudents>> findAllFiles() {
        return ResponseEntity.ok().body(filesByStudentsService.findAll());
    }

    @GetMapping("/files/{reference}")
    public ResponseEntity<FilesByStudents> findByReferenceFile(@PathVariable String reference) {
        return ResponseEntity.ok().body(filesByStudentsService.findByReference(reference));
    }

    @DeleteMapping("/files/{reference}")
    public ResponseEntity<Object> deleteByReference(@PathVariable String reference) {
        filesByStudentsService.deleteByReference(reference);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/files/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam("file")MultipartFile file,
                                             String username,
                                             FilesTypeEnum fileType) {
        filesByStudentsService.saveFile(file, username, fileType);
        return ResponseEntity.ok().body("file saved!");
    }

    // -------------------------------------------- Images by Student --------------------------------------------

    //ADM
    @GetMapping("/images")
    public ResponseEntity<List<ImagesByStudents>> findAllImages() {
        return ResponseEntity.ok().body(imagesByStudentsService.findAll());
    }

    @PostMapping("/images/upload")
    public ResponseEntity<Object> uploadImage(@RequestParam("image") MultipartFile image, String username) {
        imagesByStudentsService.saveImage(image, username);
        return ResponseEntity.ok().body("image saved!");
    }
}
