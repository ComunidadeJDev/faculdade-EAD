package com.jdev.student.repository;

import com.jdev.student.model.Materials.ImagesByStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageByStudentsRepository extends JpaRepository<ImagesByStudents, UUID> {
}
