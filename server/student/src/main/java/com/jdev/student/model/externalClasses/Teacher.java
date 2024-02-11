package com.jdev.student.model.externalClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "module_id")
    private Modules module_id;

    public Teacher() {
    }

//    public String getCompleteNameTeacher() {
//        return completeNameTeacher;
//    }
//
//    public void setCompleteNameTeacher(String completeNameTeacher) {
//        this.completeNameTeacher = completeNameTeacher;
//    }
//
//    public String getEmailTeacher() {
//        return emailTeacher;
//    }
//
//    public void setEmailTeacher(String emailTeacher) {
//        this.emailTeacher = emailTeacher;
//    }
//
//    public String getCpfTeacher() {
//        return cpfTeacher;
//    }
//
//    public void setCpfTeacher(String cpfTeacher) {
//        this.cpfTeacher = cpfTeacher;
//    }
//
//    public String getNumberHouseTeacher() {
//        return numberHouseTeacher;
//    }
//
//    public void setNumberHouseTeacher(String numberHouseTeacher) {
//        this.numberHouseTeacher = numberHouseTeacher;
//    }
//
//    public String getAddressTeacher() {
//        return addressTeacher;
//    }
//
//    public void setAddressTeacher(String addressTeacher) {
//        this.addressTeacher = addressTeacher;
//    }
//
//    public String getPhoneTeacher() {
//        return phoneTeacher;
//    }
//
//    public void setPhoneTeacher(String phoneTeacher) {
//        this.phoneTeacher = phoneTeacher;
//    }
//
//    public EtinyEnum getEthnicityTeacher() {
//        return ethnicityTeacher;
//    }
//
//    public void setEthnicityTeacher(EtinyEnum ethnicityTeacher) {
//        this.ethnicityTeacher = ethnicityTeacher;
//    }
//
//    public String getNationalityTeacher() {
//        return nationalityTeacher;
//    }
//
//    public void setNationalityTeacher(String nationalityTeacher) {
//        this.nationalityTeacher = nationalityTeacher;
//    }
//
//    public Date getBirthdayTeacher() {
//        return birthdayTeacher;
//    }
//
//    public void setBirthdayTeacher(Date birthdayTeacher) {
//        this.birthdayTeacher = birthdayTeacher;
//    }
//
//    public String getCityTeacher() {
//        return cityTeacher;
//    }
//
//    public void setCityTeacher(String cityTeacher) {
//        this.cityTeacher = cityTeacher;
//    }
//
//    public String getNumRegistrationTeacher() {
//        return numRegistrationTeacher;
//    }
//
//    public void setNumRegistrationTeacher(String numRegistrationTeacher) {
//        this.numRegistrationTeacher = numRegistrationTeacher;
//    }
}

