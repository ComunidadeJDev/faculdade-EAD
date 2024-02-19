package com.jdev.course.repository;

import com.jdev.course.model.materials.PdfMaterials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PdfMaterialRepository extends JpaRepository<PdfMaterials, UUID> {
}
