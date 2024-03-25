package com.jdev.student.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jdev.student.exceptions.customizeExceptions.FileErrorException;
import com.jdev.student.infra.mqQueue.UserForAuthenticationPublisher;
import com.jdev.student.model.DTO.StudentRegistrationDTO;
import com.jdev.student.model.DTO.StudentUpdateDTO;
import com.jdev.student.model.DTO.UserForAuthenticationDTO;
import com.jdev.student.model.Student;
import com.jdev.student.model.externalClasses.Course;
import com.jdev.student.repository.StudentRepository;
import com.jdev.student.exceptions.customizeExceptions.UserNotFoundException;
import com.jdev.student.service.externalClasses.CourseService;
import com.jdev.student.utils.GenerateNewName;
import com.jdev.student.utils.GenerateRegister;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CurriculumService curriculumService;

    @Autowired
    private FilesByStudentsService filesByStudentsService;

    @Autowired
    private UserForAuthenticationPublisher userForAuthenticationPublisher;

    //admin
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student create(StudentRegistrationDTO studentDTO) throws JsonProcessingException {
        Student studentForSave = modelingNewStudent(studentDTO);
        Student student = studentRepository.save(studentForSave);
        userForAuthenticationPublisher.sendUserForCreateAuthentication(
                new UserForAuthenticationDTO(
                        student.getCompleteName(),
                        student.getEmail(),
                        student.getPassword(),
                        "STUDENT")
        );
        return student;
    }

    private Student modelingNewStudent(StudentRegistrationDTO student) {
        return Student.builder()
                .completeName(student.completeName())
                .username(this.generateRandomUsername())
                .email(student.email())
                .password(student.password())
                .cpf(student.cpf())
                .birthday(student.birthday())
                .registration(this.generateRegistration())
                .city(student.city())
                .nationality(student.nationatily())
                .ethnicity(student.ethnicity())
                .phone(student.phone())
                .address(student.address())
                .numberHouse(student.numberHouse())
                .active(false)
                .access(false)
                .registerCourse(student.registrationCourse())
                .build();
    }

    private String generateRandomUsername() {
        String codec = GenerateNewName.generateRandomId();
        String username = codec.replaceAll("-", "0");
        Boolean confirm = findByUsernameForRegistration(username);
        if (confirm != null) {
            return username;
        } else {
            return username + UUID.randomUUID().toString().substring(0,1);
        }
    }

    private String generateRegistration() {
        String codec = GenerateRegister.newRegister();
        String registration = codec.replaceAll("-", "0");
        Boolean confirm = findByRegistrationForGenerateRegistration(registration);
        if (confirm != null) {
            return registration;
        } else {
            return registration + UUID.randomUUID().toString().substring(0, 5);
        }
    }

    //admin
    public Student findByRegistration(String registration) {
        Optional<Student> student = studentRepository.findByRegistration(registration);
        return student.orElseThrow(UserNotFoundException::new);
    }

    private Boolean findByRegistrationForGenerateRegistration(String registration) {
        Optional<Student> student = studentRepository.findByRegistration(registration);
        return student != null;
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

    public void setAsNotActive(UUID id) {
        Student student = this.findById(id);
        if (student.isActive()) {
            student.setActive(false);
            studentRepository.save(student);
        }
    }

    public void setAsActive(UUID id) {
        Student student = this.findById(id);
        if (!student.isActive()) {
            student.setActive(true);
            studentRepository.save(student);
        }
    }

    public void enableAccess(UUID id) {
        Student student = this.findById(id);
        if (!student.isAccess()) {
            student.setAccess(true);
            studentRepository.save(student);
        }
    }

    @Transactional
    public void registrationApproval(UUID id) {
        Student student = this.findById(id);
        if (student.getRgFile() != null && student.getCpfFile() != null && student.getCertificateOfCompletionFile() != null) {
            this.enableAccess(id);
            curriculumService.createCurriculum(student);
            this.setAsActive(id);
        } else {
            throw new FileErrorException("the necessary documents are not properly registered");
        }
    }

    public void sendDocumentsToTheCoodinatorForAvailable(UUID id) {
        Student student = this.findById(id);
        //send student to the coodinator for avaliable
    }
}

