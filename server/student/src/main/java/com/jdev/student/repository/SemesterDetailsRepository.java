package com.jdev.student.repository;

import com.jdev.student.model.SemesterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SemesterDetailsRepository extends JpaRepository<SemesterDetails, UUID> {
}
