package com.jdev.course.repository;

import com.jdev.course.model.Course;
import com.jdev.course.model.materials.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID> {
    Optional<Material> findByRegister(String register);

    @Query("SELECT c FROM Material c WHERE c.active = :active")
    List<Material> findAllActiveMaterials(@Param("active") boolean active);
}
