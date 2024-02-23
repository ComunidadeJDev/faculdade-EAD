package com.jdev.course.repository;

import com.jdev.course.model.CurriculumWith8Semesters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CurriculumWith8SemestersRepository extends JpaRepository<CurriculumWith8Semesters, UUID> {
}
