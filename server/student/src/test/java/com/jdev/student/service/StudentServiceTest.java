package com.jdev.student.service;

import com.jdev.student.model.FilesAndImages.FilesByStudents;
import com.jdev.student.model.FilesAndImages.ImagesByStudents;
import com.jdev.student.model.Student;
import com.jdev.student.model.enums.EtinyEnum;
import com.jdev.student.model.enums.SemesterEnum;
import com.jdev.student.model.externalClasses.Course;
import com.jdev.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    public static final String ID = "1";
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startStudents();
    }

    @Test
    void findAllStudents() {
    }

    @Test
    void create() {
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

    void startStudents() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.student = Student.builder()
                    .id(UUID.fromString(ID))
                    .completeName(COMPLETE_NAME)
                    .username(USERNAME)
                    .email(MAIL)
                    .password(PASSWORD)
                    .cpf(CPF)
                    .course(COURSE)
                    .semester(SEMESTER)
                    .birthday(dateFormat.parse("2002-01-22"))
                    .registration(REGISTRATION)
                    .city(CITY)
                    .nationality(NATIONALITY)
                    .ethnicity(ETHNICITY)
                    .phone(PHONE)
                    .imageProfile(IMAGE_PROFILE)
                    .cpfFile(CPF_FILE)
                    .rgFile(RG_FILE)
                    .certificateOfCompletionFile(CERTIFICATE)
                    .address(ADDRESS)
                    .numberHouse(NUMBER_HOUSE)
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}