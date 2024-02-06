package com.jdev.student.repository;

import com.jdev.student.model.FilesAndImages.ImagesByStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImagesByStudentsRepository extends JpaRepository<ImagesByStudents, UUID> {
    Optional<ImagesByStudents> findByReference(String reference);
}
