package com.jdev.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdev.course.model.enums.Themes;
import com.jdev.course.model.materials.Material;
import com.jdev.course.model.materials.SupportMaterial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    public String registration;

    @Column(nullable = false)
    private int duration;

    @ManyToMany(mappedBy = "module_id")
    private Set<Teacher> teachers = new HashSet<>();

    @Column(nullable = false)
    private List<Themes> themes;

    @OneToMany(mappedBy = "module_id")
    private List<SupportMaterial> supportMaterials;

    @ManyToMany(mappedBy = "module_id")
    private Set<Material> materials = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course id_course;

    @Column(nullable = false)
    private Boolean active;
}
