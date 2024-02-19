package com.jdev.course.model.materials;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideosMaterials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private String register;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "module_id")
    private Material material_id;
}
