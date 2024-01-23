package com.jdev.student.service;

import com.jdev.student.model.DTO.StudentRegistrationDTO;
import com.jdev.student.model.Student;
import com.jdev.student.model.enums.SemesterEnum;
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
        Student studentSave = modelingNewStudent(studentDTO);
        Student save = studentRepository.save(studentSave);
        return save;
    }

    private Student modelingNewStudent(StudentRegistrationDTO student) {
        Student studentForSave = new Student();
        studentForSave.setName(student.name());
        studentForSave.setEmail(student.email());
        studentForSave.setPassword(student.password());
        studentForSave.setCpf(student.cpf());
        studentForSave.setSemester(SemesterEnum.PRIMEIRO);
        studentForSave.setBirthday(student.birthday());
        studentForSave.setRegistration(generateRegistration());
        studentForSave.setCity(student.city());
        studentForSave.setNationality(student.nationatily());
        studentForSave.setEthnicity(student.ethnicity());
        studentForSave.setPhone(student.phone());
        studentForSave.setAddress(student.address());
        studentForSave.setNumberHouse(student.numberHouse());
        return studentForSave;
    }

    private String generateRegistration() {
        String registration = this.generateRandomId();
        Boolean confirm = findByRegistrationForGenerateRegistration(registration);
        if (confirm != null) {
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

    private Boolean findByRegistrationForGenerateRegistration(String registration) {
        Optional<Student> student = studentRepository.findByRegistration(registration);
        if (student != null) {
            return true;
        } else {
            return false;
        }
    }


}
