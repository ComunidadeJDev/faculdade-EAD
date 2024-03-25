package com.jdev.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SemesterDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String discipline;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "curriculum_id1")
    private Curriculum curriculum1;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "curriculum_id2")
    private Curriculum curriculum2;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "curriculum_id3")
    private Curriculum curriculum3;

//    @JsonIgnore
//    @OneToOne
//    @JoinColumn(name = "curriculum_id4")
//    private Curriculum curriculum4;
//
//    @JsonIgnore
//    @OneToOne
//    @JoinColumn(name = "curriculum_id5")
//    private Curriculum curriculum5;

//    @JsonIgnore
//    @OneToOne
//    @JoinColumn(name = "curriculum_id6")
//    private Curriculum curriculum6;
//
//    @JsonIgnore
//    @OneToOne
//    @JoinColumn(name = "curriculum_id7")
//    private Curriculum curriculum7;
//
//    @JsonIgnore
//    @OneToOne
//    @JoinColumn(name = "curriculum_id8")
//    private Curriculum curriculum8;
}
