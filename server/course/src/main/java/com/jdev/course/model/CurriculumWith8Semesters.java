package com.jdev.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumWith8Semesters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToMany(mappedBy = "curriculum_id")
    private Set<Discipline> semester1;

    @ManyToMany(mappedBy = "curriculum_id")
    private Set<Discipline> semester2;

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

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course_id;
}
