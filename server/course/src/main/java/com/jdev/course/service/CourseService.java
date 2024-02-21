package com.jdev.course.service;

import com.jdev.course.exceptions.CusmotomizeException.CourseErrorException;
import com.jdev.course.exceptions.CusmotomizeException.CourseNotFoundException;
import com.jdev.course.exceptions.CusmotomizeException.CourseAlreadyExistsException;
import com.jdev.course.model.Course;
import com.jdev.course.model.DTO.CourseCreateDTO;
import com.jdev.course.model.DTO.CourseUpdateDTO;
import com.jdev.course.repository.CourseRepository;
import com.jdev.course.utils.GenerateRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public List<Course> findAllActiveCourses() {
        return courseRepository.findAllActiveCourses(true);
    }

    public Course create(CourseCreateDTO courseDTO) {
        if (courseRepository.findByName(courseDTO.name()).isEmpty()) {
            Course courseForSave = this.modelingNewCourseForSave(courseDTO);
            return this.courseRepository.save(courseForSave);
        } else {
            throw new CourseAlreadyExistsException();
        }
    }

    private Course modelingNewCourseForSave(CourseCreateDTO CourseDTO) {
        return Course.builder()
                .name(CourseDTO.name())
                .modules(List.of())
                .created(LocalDate.now())
                .quantityMaterials(0)
                .quantityModules(0)
                .registration(GenerateRegister.newRegister())
                .active(true)
                .build();
    }

    public Course update(CourseUpdateDTO courseUpdateDTO) {
        Optional<Course> course = courseRepository.findByRegistration(courseUpdateDTO.registration());
        if (course.isPresent()) {
            if (courseUpdateDTO.name().isBlank() || courseUpdateDTO.name().isEmpty()) {
                throw new CourseErrorException("this name not valid!");
            } else {
                course.get().setName(courseUpdateDTO.name());
                return courseRepository.save(course.get());
            }
        } else {
            throw new CourseNotFoundException();
        }
    }

    public Course findByCourseWithName(String name) {
        Optional<Course> course = courseRepository.findByName(name);
        return course.orElseThrow(CourseNotFoundException::new);
    }

    public Course findByCourseWithRegistration(String registration) {
        Optional<Course> course = courseRepository.findByRegistration(registration);
        return course.orElseThrow(CourseNotFoundException::new);
    }

    public void setWithNotActive(String registration) {
        Course course = this.findByCourseWithRegistration(registration);
        course.setActive(false);
        courseRepository.save(course);
    }

    public void addModulesUnit(Course course) {
        course.setQuantityModules(course.getQuantityModules() + 1);
        courseRepository.save(course);
    }

    public void addMaterialUnit(Course course) {
        course.setQuantityMaterials(course.getQuantityMaterials() + 1);
        courseRepository.save(course);
    }
}
