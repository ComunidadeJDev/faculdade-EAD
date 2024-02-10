package com.jdev.student.model.DTO;

import com.jdev.student.model.enums.EtinyEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

public record TeacherRegistrationDTO (

     String completeNameTeacher,
     String emailTeacher,
     String cpfTeacher,
     String numberHouseTeacher,
     String addressTeacher,
     String phoneTeacher,
     EtinyEnum ethnicityTeacher,
     String nationalityTeacher,
     Date birthdayTeacher,
     String cityTeacher

){
}
