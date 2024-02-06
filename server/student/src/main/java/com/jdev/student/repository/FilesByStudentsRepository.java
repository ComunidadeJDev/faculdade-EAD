package com.jdev.student.repository;

import com.jdev.student.model.FilesAndImages.FilesByStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FilesByStudentsRepository extends JpaRepository<FilesByStudents, UUID> {

    Optional<FilesByStudents> findByReference(String reference);
}
