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

    @ManyToMany(mappedBy = "curriculum_id")
    private Set<Discipline> semester1 = new HashSet<>();

    @ManyToMany(mappedBy = "curriculum_id")
    private Set<Discipline> semester2 = new HashSet<>();

//    @Column
//    private Set<Discipline> semester3;
//
//    @Column
//    private Set<Discipline> semester4;
//
//    @Column
//    private Set<Discipline> semester5;
//
//    @Column
//    private Set<Discipline> semester6;
//
//    @Column
//    private Set<Discipline> semester7;
//
//    @Column
//    private Set<Discipline> semester8;

    @OneToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course_id;

    public String toString() {
        return "CurriculumWith8Semesters.CurriculumWith8SemestersBuilder(id=" + this.id + ", semester1=" + this.semester1
                + ", semester2=" + this.semester2 + ")";
    }
}
