package com.jdev.student.model;

import com.jdev.student.model.FilesAndImages.FilesByStudents;
import com.jdev.student.model.FilesAndImages.ImagesByStudents;
import com.jdev.student.model.enums.EtinyEnum;
import com.jdev.student.model.enums.SemesterEnum;
import com.jdev.student.model.externalClasses.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 80)
    @NotBlank
    private String completeName;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String username;

    @Email
    @Column(nullable = false, unique = true, length = 60)
    @NotBlank
    private String email;

    @Column(nullable = false, length = 20)
    @NotBlank
    private String password;

    @CPF
    @Column(nullable = false, unique = true, length = 20)
    @NotBlank
    private String cpf;

    @ManyToMany(mappedBy = "student_id")
    private Set<Course> course;

    @Column(nullable = false)
    private SemesterEnum semester;

    @Column(nullable = false)
    @NotNull
    private Date birthday;

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank
    @NotNull
    private String registration;

    @Column(nullable = false)
    @NotBlank
    private String city;

    @Column(nullable = false)
    @NotBlank
    private String nationality;

    @Column(nullable = false)
    private EtinyEnum ethnicity;

    @Column(nullable = false, unique = true, length = 15)
    @NotBlank
    @NotNull
    private String phone;

    @OneToOne(mappedBy = "student_id")
    private ImagesByStudents imageProfile;

    @OneToOne(mappedBy = "cpfFile")
    private FilesByStudents cpfFile;

    @OneToOne(mappedBy = "rgFile")
    private FilesByStudents rgFile;

    @OneToOne(mappedBy = "completionFile")
    private FilesByStudents certificateOfCompletionFile;

    @Column(nullable = false, length = 80)
    @NotBlank
    private String address;

    @Column(nullable = false, length = 10)
    @NotBlank
    @NotNull
    private String numberHouse;
}
