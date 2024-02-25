package com.jdev.course.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

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
    private List<Discipline> semester1 = new ArrayList<>();

    @ManyToMany(mappedBy = "curriculum_id_semester2")
    private List<Discipline> semester2 = new ArrayList<>();

    @ManyToMany(mappedBy = "curriculum_id_semester3")
    private List<Discipline> semester3 = new ArrayList<>();

    @ManyToMany(mappedBy = "curriculum_id_semester4")
    private List<Discipline> semester4 = new ArrayList<>();

    @ManyToMany(mappedBy = "curriculum_id_semester5")
    private List<Discipline> semester5 = new ArrayList<>();

    @ManyToMany(mappedBy = "curriculum_id_semester6")
    private List<Discipline> semester6 = new ArrayList<>();

    @ManyToMany(mappedBy = "curriculum_id_semester7")
    private List<Discipline> semester7 = new ArrayList<>();

    @ManyToMany(mappedBy = "curriculum_id_semester8")
    private List<Discipline> semester8 = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course_id;

    public String toString() {
        return "CurriculumWith8Semesters.CurriculumWith8SemestersBuilder(id=" + this.id + ", semester1=" + this.semester1
                + ", semester2=" + this.semester2 + ")";
    }
}
