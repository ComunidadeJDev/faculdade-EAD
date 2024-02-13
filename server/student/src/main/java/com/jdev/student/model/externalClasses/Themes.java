package com.jdev.student.model.externalClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;

import lombok.Data;


@Entity
@Table(name = "theme_course")
@Data

public class Themes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "module_themes")
    private Modules module_themes;
}
