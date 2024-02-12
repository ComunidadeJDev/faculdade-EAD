package com.jdev.student.model.externalClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
public class Material {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 60)
    @NotBlank
    private String name;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "module_material")
    private Modules module_material;
}
