package com.jdev.student.service;

import com.jdev.student.model.DTO.StudentRegistrationDTO;
import com.jdev.student.model.DTO.StudentUpdateDTO;
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
        studentForSave.setCompleteName(student.completeName());
        studentForSave.setUsername(generateRandomUsername());
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

    private String generateRandomUsername() {
        String username = this.generateRandomUUID();
        Boolean confirm = findByUsernameForRegistration(username);
        if(confirm != null) {
            return username;
        } else {
            return username + UUID.randomUUID().toString().substring(0,1);
        }
    }

    private String generateRegistration() {
        String registration = this.generateRandomUUID();
        Boolean confirm = findByRegistrationForGenerateRegistration(registration);
        if (confirm != null) {
            return registration;
        } else {
            return registration + UUID.randomUUID().toString().substring(0, 5);
        }
    }

    private String generateRandomUUID() {
        return UUID.randomUUID().toString().substring(0, 10);
    }

    //admin
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

    //admin
    public Student findById(UUID id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElseThrow(() -> new RuntimeException("student not found!"));
    }

    //admin
    public Boolean findByUsernameForRegistration(String username) {
        Optional<Student> student = studentRepository.findByUsername(username);
        return (student != null) ? true : false;
    }

    public Student updateStudent(StudentUpdateDTO studentUpdate) {
        Student student = this.findByRegistration(studentUpdate.registration());
        student.setCompleteName(studentUpdate.completeName());
        student.setEmail(studentUpdate.email());
        student.setCpf(studentUpdate.cpf());
        student.setBirthday(studentUpdate.birthday());
        student.setCity(studentUpdate.city());
        student.setNationality(studentUpdate.nationatily());
        student.setEthnicity(studentUpdate.ethnicity());
        student.setPhone(studentUpdate.phone());
        student.setAddress(studentUpdate.address());
        student.setNumberHouse(studentUpdate.numberHouse());
        return studentRepository.save(student);
    }
}
