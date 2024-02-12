package com.jdev.student.model.externalClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Table
public class Modules {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false , length = 50)
    @NotBlank
    private String name;

    @NotNull
    private Integer duration;

    @OneToOne(mappedBy = "module_teacher")
    private List<Teacher> teacher;

    @OneToMany(mappedBy = "module_themes")
    private List<Themes> themes;

    //@OneToOne
    //private List<MaterialSupport> supportMaterial;

    @OneToOne(mappedBy = "module_material")
    private List<Material> material;

    private boolean finished;


}
