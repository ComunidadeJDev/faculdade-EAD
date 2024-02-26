package com.jdev.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdev.student.model.enums.SemesterEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private SemesterEnum semester;

    @OneToOne(mappedBy = "curriculum1")
    private SemesterDetails semester1;

    @OneToOne(mappedBy = "curriculum2")
    private SemesterDetails semester2;

    @OneToOne(mappedBy = "curriculum3")
    private SemesterDetails semester3;

//    @OneToOne(mappedBy = "curriculum4")
//    private SemesterDetails semester4;
//
//    @OneToOne(mappedBy = "curriculum5")
//    private SemesterDetails semester5;

//    @OneToOne(mappedBy = "curriculum6")
//    private SemesterDetails semester6;
//
//    @OneToOne(mappedBy = "curriculum7")
//    private SemesterDetails semester7;
//
//    @OneToOne(mappedBy = "curriculum8")
//    private SemesterDetails semester8;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student_id;
}
