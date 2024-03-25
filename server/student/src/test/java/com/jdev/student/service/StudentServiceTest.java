package com.jdev.student.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jdev.student.model.DTO.StudentRegistrationDTO;
import com.jdev.student.model.FilesAndImages.FilesByStudents;
import com.jdev.student.model.FilesAndImages.ImagesByStudents;
import com.jdev.student.model.Student;
import com.jdev.student.model.enums.EtinyEnum;
import com.jdev.student.model.enums.SemesterEnum;
import com.jdev.student.model.externalClasses.Course;
import com.jdev.student.repository.StudentRepository;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.validation.constraints.Email;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
class StudentServiceTest {

    public static final String ID = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
    public static final String COMPLETE_NAME = "Gabriel Cruz Rodrigues";
    public static final String USERNAME = "3c2363a5-9";
    public static final String MAIL = "gabriel@gmail.com";
    public static final String PASSWORD = "123456";
    public static final String CPF = "86859683058";
    public static final Set<Course> COURSE = null;
    public static final SemesterEnum SEMESTER = SemesterEnum.PRIMEIRO;
    public static final String REGISTRATION = "2be381a1-a";
    public static final String CITY = "jequié";
    public static final String NATIONALITY = "brasileira";
    public static final EtinyEnum ETHNICITY = EtinyEnum.PRETO;
    public static final String PHONE = "73900000000";
    public static final ImagesByStudents IMAGE_PROFILE = new ImagesByStudents();
    public static final FilesByStudents CPF_FILE = new FilesByStudents();
    public static final FilesByStudents RG_FILE = new FilesByStudents();
    public static final FilesByStudents CERTIFICATE = new FilesByStudents();
    public static final String ADDRESS = "rua a";
    public static final String NUMBER_HOUSE = "64";

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    private Student student;
    private StudentRegistrationDTO studentRegistrationDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startStudents();
    }

    private void startStudents() {
    }

    @Test
    void mustReturnAListOfStudents_whenCallingToFindAllStudents() {
        when(studentRepository.findAll()).thenReturn(List.of(student));
        List<Student> response = studentService.findAllStudents();
        assertNotNull(response);
        assertEquals(Student.class, response.get(0).getClass());
        assertEquals(1, response.size());
        assertEquals(ID, response.get(0).getId().toString());
        assertEquals(COMPLETE_NAME, response.get(0).getCompleteName());
        assertEquals(USERNAME, response.get(0).getUsername());
        assertEquals(MAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());
        assertEquals(REGISTRATION, response.get(0).getRegistration());
        assertEquals(CITY, response.get(0).getCity());
        assertEquals(NATIONALITY, response.get(0).getNationality());
        assertEquals(ETHNICITY, response.get(0).getEthnicity());
        assertEquals(PHONE, response.get(0).getPhone());
        assertEquals(IMAGE_PROFILE, response.get(0).getImageProfile());
        assertEquals(CPF_FILE, response.get(0).getCpfFile());
        assertEquals(RG_FILE, response.get(0).getRgFile());
        assertEquals(CERTIFICATE, response.get(0).getCertificateOfCompletionFile());
        assertEquals(ADDRESS, response.get(0).getAddress());
        assertEquals(NUMBER_HOUSE, response.get(0).getNumberHouse());
    }

    @Test
    void mustReturnANewStudent_whenCallingToCreate() throws JsonProcessingException {
        when(studentRepository.save(any())).thenReturn(student);
        Student response = studentService.create(studentRegistrationDTO);
        assertEquals(ID, response.getId().toString());
        assertEquals(Student.class, response.getClass());
        assertEquals(COMPLETE_NAME, response.getCompleteName());
        assertEquals(USERNAME, response.getUsername());
        assertEquals(MAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(REGISTRATION, response.getRegistration());
        assertEquals(CITY, response.getCity());
        assertEquals(NATIONALITY, response.getNationality());
        assertEquals(ETHNICITY, response.getEthnicity());
        assertEquals(PHONE, response.getPhone());
        assertEquals(IMAGE_PROFILE, response.getImageProfile());
        assertEquals(CPF_FILE, response.getCpfFile());
        assertEquals(RG_FILE, response.getRgFile());
        assertEquals(CERTIFICATE, response.getCertificateOfCompletionFile());
        assertEquals(ADDRESS, response.getAddress());
        assertEquals(NUMBER_HOUSE, response.getNumberHouse());
    }

    @Test
    void findByRegistration() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByUsernameForRegistration() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }
}