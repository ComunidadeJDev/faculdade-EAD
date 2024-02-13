package com.jdev.student.repository;

import com.jdev.student.model.externalClasses.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModuleRepository extends JpaRepository<Module, UUID> {
    Optional<Module> findByName(String name);
}
