package com.jdev.course.repository;

import com.jdev.course.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, UUID> {
    Optional<Discipline> findByName(String name);
    Optional<Discipline> findByRegistration(String registration);

    @Query("SELECT c FROM Discipline c WHERE c.active = :active")
    List<Discipline> findAllActiveCourses(@Param("active") boolean active);
}
