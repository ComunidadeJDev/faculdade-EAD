package com.jdev.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdev.course.model.enums.ThemesEnum;
import com.jdev.course.model.materials.Material;
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
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    public String registration;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private int quantityMaterials;

    @ManyToMany(mappedBy = "discipline_id")
    private Set<Teacher> teachers = new HashSet<>();

    @Column(nullable = false)
    private List<ThemesEnum> themes;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "discipline_id")
    private List<Material> materials;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester1",
                joinColumns = @JoinColumn(name = "discipline_fk"),
                inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private List<CurriculumWith8Semesters> curriculum_id_semester1;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester2",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private List<CurriculumWith8Semesters> curriculum_id_semester2;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester3",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private List<CurriculumWith8Semesters> curriculum_id_semester3;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester4",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private List<CurriculumWith8Semesters> curriculum_id_semester4;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester5",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private List<CurriculumWith8Semesters> curriculum_id_semester5;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester6",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private List<CurriculumWith8Semesters> curriculum_id_semester6;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester7",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private List<CurriculumWith8Semesters> curriculum_id_semester7;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "curriculum_discipline_semester8",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "curriculum_fk"))
    private List<CurriculumWith8Semesters> curriculum_id_semester8;

    public String toString() {
        UUID var10000 = this.getId();
        return "Discipline(id=" + var10000 + ", name=" + this.getName() + ", registration=" + this.getRegistration() + ", duration="
                + this.getDuration() + ", quantityMaterials=" + this.getQuantityMaterials() + ", teachers=" + this.getTeachers()
                + ", themes=" + this.getThemes() + ", supportMaterials=" + ", active=" + this.getActive() + ", materials=" + this.getMaterials() + ")";
            /*    + ", curriculum_id_semester1=" + this.getCurriculum_id_semester1()
                + ", curriculum_id_semester2=" + this.getCurriculum_id_semester2() + ", curriculum_id_semester3="
                + this.getCurriculum_id_semester3() + ", curriculum_id_semester4=" + this.getCurriculum_id_semester4()
                + ", curriculum_id_semester5=" + this.getCurriculum_id_semester5() + ", curriculum_id_semester6="
                + this.getCurriculum_id_semester6() + ", curriculum_id_semester7=" + this.getCurriculum_id_semester7()
                + ", curriculum_id_semester8=" + this.getCurriculum_id_semester8() + ")";*/
    }
}
