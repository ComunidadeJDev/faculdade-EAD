package com.jdev.student.model.externalClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "modules_course" )
public class Module {   //verify

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false , length = 50)
    @NotBlank
    private String name;

    @NotNull
    private Integer duration;

    @OneToMany(mappedBy = "module_themes")
    private List<Themes> themes;

    private boolean finished;
}
