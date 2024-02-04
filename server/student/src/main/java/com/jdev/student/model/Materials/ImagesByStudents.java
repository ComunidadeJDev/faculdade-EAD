package com.jdev.student.model.Materials;

import com.jdev.student.model.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table
@Data
public class ImagesByStudents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String Reference;

    @Column(nullable = false)
    private String Register;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student_id;
}
