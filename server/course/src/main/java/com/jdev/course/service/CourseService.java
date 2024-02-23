package com.jdev.course.service;

import com.jdev.course.exceptions.CusmotomizeException.CourseErrorException;
import com.jdev.course.exceptions.CusmotomizeException.CourseNotFoundException;
import com.jdev.course.exceptions.CusmotomizeException.CourseAlreadyExistsException;
import com.jdev.course.model.Course;
import com.jdev.course.model.CurriculumWith8Semesters;
import com.jdev.course.model.DTO.AddDisciplineToTheCourseDTO;
import com.jdev.course.model.DTO.CourseCreateDTO;
import com.jdev.course.model.DTO.CourseUpdateDTO;
import com.jdev.course.model.Discipline;
import com.jdev.course.model.enums.SemesterEnum;
import com.jdev.course.repository.CourseRepository;
import com.jdev.course.repository.DisciplineRepository;
import com.jdev.course.utils.GenerateRegister;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CurriculumWith8SemestersService curriculumService;

    @Autowired
    private DisciplineRepository disciplineRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public List<Course> findAllActiveCourses() {
        return courseRepository.findAllActiveCourses(true);
    }

    public Course create(CourseCreateDTO courseDTO) {
        if (courseRepository.findByName(courseDTO.name()).isEmpty()) {
            Course courseForSave = this.modelingNewCourseForSave(courseDTO);
            Course courseSaved = this.courseRepository.save(courseForSave);
            curriculumService.setCourseIdInCurriculum(courseSaved);
            return courseSaved;
        } else {
            throw new CourseAlreadyExistsException();
        }
    }

    public void addDisciplineToTheCourse(AddDisciplineToTheCourseDTO data) {
        Optional<Discipline> discipline = disciplineRepository.findByRegistration(data.registerDiscipline());
        if (discipline.isPresent()) {
            Course course = this.findByCourseWithRegistration(data.registerCourse());
            Set<Discipline> disciplines = course.getCurriculum().getSemester1();
            disciplines.add(discipline.get());
            course.getCurriculum().setSemester1(disciplines);
            courseRepository.save(course);
        } else {
            throw new RuntimeException("discipline not found");
        }
    }

    private Course modelingNewCourseForSave(CourseCreateDTO CourseDTO) {
        CurriculumWith8Semesters curriculum = curriculumService.createCurriculum();

        return Course.builder()
                .name(CourseDTO.name())
                .curriculum(curriculum)
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
