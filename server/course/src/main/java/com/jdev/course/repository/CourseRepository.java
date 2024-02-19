package com.jdev.course.repository;

import com.jdev.course.model.Course;
import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Optional<Course> findByName(String name);
    Optional<Course> findByRegistration(String registration);

    @Query("SELECT c FROM Course c WHERE c.active = :active")
    List<Course> findAllActiveCourses(@Param("active") boolean active);
}
