package com.jdev.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdev.student.model.enums.SemesterEnum;
import com.jdev.student.model.externalClasses.Course;
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

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student_id;
}
