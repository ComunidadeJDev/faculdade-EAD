package com.jdev.course.repository;

import com.jdev.course.model.Course;
import com.jdev.course.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModuleRepository extends JpaRepository<Module, UUID> {
    Optional<Module> findByName(String name);
    Optional<Module> findByRegistration(String registration);

    @Query("SELECT c FROM Module c WHERE c.active = :active")
    List<Module> findAllActiveCourses(@Param("active") boolean active);
}
