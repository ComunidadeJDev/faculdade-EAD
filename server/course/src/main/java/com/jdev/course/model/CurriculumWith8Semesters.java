package com.jdev.course.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumWith8Semesters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToMany(mappedBy = "curriculum_id_semester1")
    private Set<Discipline> semester1 = new HashSet<>();

    @ManyToMany(mappedBy = "curriculum_id_semester2")
    private Set<Discipline> semester2 = new HashSet<>();

    @ManyToMany(mappedBy = "curriculum_id_semester3")
    private Set<Discipline> semester3 = new HashSet<>();

    @ManyToMany(mappedBy = "curriculum_id_semester4")
    private Set<Discipline> semester4 = new HashSet<>();

    @ManyToMany(mappedBy = "curriculum_id_semester5")
    private Set<Discipline> semester5 = new HashSet<>();

    @ManyToMany(mappedBy = "curriculum_id_semester6")
    private Set<Discipline> semester6 = new HashSet<>();

    @ManyToMany(mappedBy = "curriculum_id_semester7")
    private Set<Discipline> semester7 = new HashSet<>();

    @ManyToMany(mappedBy = "curriculum_id_semester8")
    private Set<Discipline> semester8 = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course_id;

    public String toString() {
        return "CurriculumWith8Semesters.CurriculumWith8SemestersBuilder(id=" + this.id + ", semester1=" + this.semester1
                + ", semester2=" + this.semester2 + ")";
    }
}
