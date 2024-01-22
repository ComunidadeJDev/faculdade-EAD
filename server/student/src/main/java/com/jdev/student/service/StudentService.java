package com.jdev.student.service;

import com.jdev.student.model.DTO.StudentRegistrationDTO;
import com.jdev.student.model.Student;
import com.jdev.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    //admin
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student create(StudentRegistrationDTO studentDTO) {
        Student studentforSave = modelingNewStudent(studentDTO);

    }

    private String generateRegistration() {
        String registration = this.generateRandomId();
        Student student = findByRegistration(registration);
        if (student != null) {
            return registration;
        } else {
            return registration + UUID.randomUUID().toString().substring(0, 5);
        }
    }

    private String generateRandomId() {
        return UUID.randomUUID().toString().substring(0, 10);
    }

    public Student findByRegistration(String registration) {
        Optional<Student> student = studentRepository.findByRegistration(registration);
        return student.orElseThrow(() -> new RuntimeException("student not found"));
    }


}
