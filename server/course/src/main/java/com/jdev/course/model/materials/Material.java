package com.jdev.course.model.materials;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdev.course.model.Module;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Data
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

    @OneToMany(mappedBy = "material_id")
    private List<PdfMaterials> pdfMaterials;

    @OneToMany(mappedBy = "material_id")
    private List<ImagesMaterials> imagesMaterials;

    @OneToMany(mappedBy = "material_id")
    private List<VideosMaterials> videosMaterials;

    @JsonIgnore
    @ManyToMany
    @JoinTable (
            name = "material_module",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id")
    )
    private Set<Module> module_id = new HashSet<>();

}
