package com.jdev.student.controller;

import com.jdev.student.model.DTO.StudentRegistrationDTO;
import com.jdev.student.model.DTO.StudentUpdateDTO;
import com.jdev.student.model.DTO.TeacherRegistrationDTO;
import com.jdev.student.model.FilesAndImages.FilesByStudents;
import com.jdev.student.model.FilesAndImages.ImagesByStudents;
import com.jdev.student.model.Student;
import com.jdev.student.model.enums.FilesTypeEnum;
import com.jdev.student.model.externalClasses.Teacher;
import com.jdev.student.service.FilesByStudentsService;
import com.jdev.student.service.ImagesByStudentsService;
import com.jdev.student.service.StudentService;
import com.jdev.student.service.externalClasses.TeacherService;
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

    @Autowired
    private TeacherService teacherService;

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
 
    @DeleteMapping("/disable/{id}")
    public ResponseEntity<Student> setAsNotActive(@PathVariable UUID id){
        studentService.setAsNotActive(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/enable/{id}")
    public ResponseEntity<Student> setAsActive(@PathVariable UUID id){
        studentService.setAsActive(id);
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
    public ResponseEntity<Object> deleteByReferenceFile(@PathVariable String reference) {
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

    @GetMapping("/images")
    public ResponseEntity<List<ImagesByStudents>> findAllImages() {
        return ResponseEntity.ok().body(imagesByStudentsService.findAll());
    }

    @GetMapping("/images/{reference}")
    public ResponseEntity<ImagesByStudents> findByReferenceImage(@PathVariable String reference) {
        return ResponseEntity.ok().body(imagesByStudentsService.findByReference(reference));
    }

    @DeleteMapping("/images/{reference}")
    public ResponseEntity<Object> deleteByReferenceImage(@PathVariable String reference) {
        imagesByStudentsService.deleteByReference(reference);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/images/upload")
    public ResponseEntity<Object> uploadImage(@RequestParam("image") MultipartFile image, String username) {
        imagesByStudentsService.saveImage(image, username);
        return ResponseEntity.ok().body("image saved!");
    }

    // -------------------------------------------- Teacher --------------------------------------------

    @PostMapping("/teacher")
    public ResponseEntity<Teacher> create(@RequestBody @Valid TeacherRegistrationDTO teacher){
        return ResponseEntity.ok().body(teacherService.create(teacher));
    }

    @GetMapping("/teacher")
    public ResponseEntity<List<Teacher>> findAllTeachers() {
        return ResponseEntity.ok().body(teacherService.findAllTeachers());
    }
}
