package com.jdev.student.model.externalClasses;

import com.jdev.student.model.Student;
import jakarta.persistence.*;
import lombok.Data;

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

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student_id;

}
