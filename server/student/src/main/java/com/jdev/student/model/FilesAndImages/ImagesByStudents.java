package com.jdev.student.model.FilesAndImages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdev.student.model.Student;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
public class ImagesByStudents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private String register;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student_id;

    public ImagesByStudents(String fileName, String register, Student student) {
        this.reference = fileName;
        this.register = register;
        this.student_id = student;
    }
}
