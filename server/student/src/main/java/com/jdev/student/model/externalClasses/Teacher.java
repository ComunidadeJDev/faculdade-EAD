package com.jdev.student.model.externalClasses;

import com.jdev.student.model.enums.EtinyEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false,length = 80)
    @NotBlank
    private String completeNameTeacher;

    @Email
    @Column(nullable = false,unique = true, length = 60)
    @NotBlank
    private String emailTeacher;

    @CPF
    @Column(nullable = false,unique = true, length = 20)
    @NotBlank
    private String cpfTeacher;

    @Column(nullable = false, length = 80)
    @NotBlank
    private String numberHouseTeacher;

    @Column(nullable = false, length = 80)
    @NotBlank
    private String addressTeacher;

    @Column(nullable = false ,  length = 11)
    @NotBlank
    private String phoneTeacher;

    @Column(nullable = false)
    private EtinyEnum ethnicityTeacher;

    @Column(nullable = false)
    @NotBlank
    private String nationalityTeacher;

    @Column(nullable = false)
    @NotNull
    private Date birthdayTeacher;


    @Column(nullable = false)
    @NotBlank
    private String cityTeacher;

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank
    private String numRegistrationTeacher;

    @OneToOne
    @JoinColumn(name = "module_teacher")
    private Modules module_teacher;

    public Teacher() {
    }


}

