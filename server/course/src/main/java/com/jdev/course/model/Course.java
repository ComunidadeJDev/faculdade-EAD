package com.jdev.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "id_course")
    @Column(nullable = false)
    private List<Module> modules;

    @Column(nullable = false)
    private LocalDate created;

    @Column(nullable = false)
    private int quantityMaterials;

    @Column(nullable = false)
    private int quantityModules;

    @Column(nullable = false)
    private String registration;

    @Column(nullable = false)
    private Boolean active;
}