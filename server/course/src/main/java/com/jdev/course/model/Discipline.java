package com.jdev.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdev.course.model.enums.ThemesEnum;
import com.jdev.course.model.materials.Material;
import com.jdev.course.model.materials.SupportMaterial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

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
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    public String registration;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private int quantityMaterials;

    @ManyToMany(mappedBy = "discipline_id")
    private Set<Teacher> teachers = new HashSet<>();

    @Column(nullable = false)
    private List<ThemesEnum> themes;

    @OneToMany(mappedBy = "discipline_id")
    private List<SupportMaterial> supportMaterials;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "discipline_id")
    private List<Material> materials;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester1",
                joinColumns = @JoinColumn(name = "discipline_fk"),
                inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private Set<CurriculumWith8Semesters> curriculum_id_semester1;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester2",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private Set<CurriculumWith8Semesters> curriculum_id_semester2;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester3",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private Set<CurriculumWith8Semesters> curriculum_id_semester3;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester4",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private Set<CurriculumWith8Semesters> curriculum_id_semester4;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester5",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private Set<CurriculumWith8Semesters> curriculum_id_semester5;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester6",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private Set<CurriculumWith8Semesters> curriculum_id_semester6;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester7",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private Set<CurriculumWith8Semesters> curriculum_id_semester7;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester8",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private Set<CurriculumWith8Semesters> curriculum_id_semester8;
}
