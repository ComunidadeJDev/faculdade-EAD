package com.jdev.course.model.materials;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdev.course.model.Module;
import com.jdev.course.model.enums.MaterialTypeEnum;
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
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String register;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private MaterialTypeEnum type;

    @Column(nullable = false)
    private Boolean active;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module_id;
}
