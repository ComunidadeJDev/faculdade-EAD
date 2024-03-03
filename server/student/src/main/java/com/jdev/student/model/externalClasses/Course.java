package com.jdev.student.model.externalClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdev.student.model.Curriculum;
import com.jdev.student.model.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String registration;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Curriculum> curriculum_id;

}
