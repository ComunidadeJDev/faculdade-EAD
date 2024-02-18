package com.jdev.course.model.materials;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table
@Data
public class PdfMaterials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private String register;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material_id;
}
