package com.jdev.course.service;

import com.jdev.course.exceptions.CusmotomizeException.CourseErrorException;
import com.jdev.course.exceptions.CusmotomizeException.CourseNotFoundException;
import com.jdev.course.exceptions.CusmotomizeException.CourseAlreadyExistsException;
import com.jdev.course.exceptions.CusmotomizeException.DisciplineNotFoundException;
import com.jdev.course.model.Course;
import com.jdev.course.model.CurriculumWith8Semesters;
import com.jdev.course.model.DTO.AddDisciplineToTheCourseDTO;
import com.jdev.course.model.DTO.CourseCreateDTO;
import com.jdev.course.model.DTO.CourseUpdateDTO;
import com.jdev.course.model.Discipline;
import com.jdev.course.model.enums.SemesterEnum;
import com.jdev.course.repository.CourseRepository;
import com.jdev.course.repository.CurriculumWith8SemestersRepository;
import com.jdev.course.repository.DisciplineRepository;
import com.jdev.course.utils.GenerateRegister;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CurriculumWith8SemestersRepository curriculumRepository;

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
            this.addCurriculumToTheCourse(courseSaved);
            return courseSaved;
        } else {
            throw new CourseAlreadyExistsException();
        }
    }

    private void addCurriculumToTheCourse(Course course) {
        CurriculumWith8Semesters curriculum = CurriculumWith8Semesters.builder()
                .semester1(new HashSet<>())
                .semester2(new HashSet<>())
                .course_id(course)
                .build();
        curriculumRepository.save(curriculum);
    }

    public void addDisciplineToTheCourse(AddDisciplineToTheCourseDTO data) {
        Discipline discipline = disciplineRepository.findByRegistration(data.registerDiscipline())
                .orElseThrow(DisciplineNotFoundException::new);

        Course course = this.findByCourseWithRegistration(data.registerCourse());

        CurriculumWith8Semesters curriculum = curriculumRepository.findById(course.getCurriculum().getId())
                .orElseThrow(RuntimeException::new);

        if (data.semester() == SemesterEnum.PRIMEIRO) {
            Set<Discipline> semester1 = curriculum.getSemester1();
            semester1.add(discipline);
            curriculum.setSemester1(semester1);
            curriculumRepository.save(curriculum);

            Set<CurriculumWith8Semesters> curriculum1 = new HashSet<>();
            curriculum1.add(curriculum);
            discipline.setCurriculum_id(curriculum1);
            disciplineRepository.save(discipline);
        }
//        } else if (data.semester() == SemesterEnum.SEGUNDO) {
//            CurriculumWith8Semesters curriculum = course.getCurriculum();
//            Set<Discipline> semester2 = curriculum.getSemester2();
//            semester2.add(discipline);
//            curriculum.setSemester1(semester2);
//
//            curriculumRepository.save(curriculum);
//        }
    }

    private Course modelingNewCourseForSave(CourseCreateDTO CourseDTO) {
        Course course = Course.builder()
                .name(CourseDTO.name())
                .created(LocalDate.now())
                .quantityMaterials(0)
                .quantityModules(0)
                .registration(GenerateRegister.newRegister())
                .active(true)
                .build();

        return courseRepository.save(course);
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
