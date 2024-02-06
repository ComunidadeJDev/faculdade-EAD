package com.jdev.student.model.FilesAndImages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdev.student.model.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table
@Data
public class FilesByStudents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private String register;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "cpfFile")
    private Student cpfFile;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "rgFile")
    private Student rgFile;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "completionFile")
    private Student completionFile;

    public FilesByStudents(){}

    public FilesByStudents(String newFileName, String register, Student cpf, Student rg, Student completion) {
        this.reference = newFileName;
        this.register = register;
        this.cpfFile = cpf;
        this.rgFile = rg;
        this.completionFile = completion;
    }
}
