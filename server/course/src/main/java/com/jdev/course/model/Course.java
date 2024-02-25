package com.jdev.course.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate created;

    @Column(nullable = false)
    private int quantityDisciplines;

    @Column(nullable = false)
    private String registration;

    @Column(nullable = false)
    private Boolean active;

    @OneToOne(mappedBy = "course_id", fetch = FetchType.EAGER)
    @JsonManagedReference
    private CurriculumWith8Semesters curriculum;

    public String toString() {
        UUID var10000 = this.getId();
        return "Course(id=" + var10000 + ", name=" + this.getName() + ", created=" + this.getCreated() + ", quantityMaterials="
                + ", quantityModules=" + this.getQuantityDisciplines() + ", registration="
                + this.getRegistration() + ", active=" + this.getActive() + ", curriculum=" + this.getCurriculum() + ")";
    }
}