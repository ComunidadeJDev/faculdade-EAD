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
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false,length = 80)
    @NotBlank
    private String completeNameTeacher;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "module_id")
    private Modules module_id;
}

