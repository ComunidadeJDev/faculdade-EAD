package com.jdev.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @OneToOne(mappedBy = "course_id", fetch = FetchType.EAGER)
    private CurriculumWith8Semesters curriculum;
}