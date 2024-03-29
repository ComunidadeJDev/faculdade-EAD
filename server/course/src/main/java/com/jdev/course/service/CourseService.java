package com.jdev.course.service;

import com.jdev.course.exceptions.CusmotomizeException.*;
import com.jdev.course.infra.mqQueues.RequestCoursesForStudentPublisher;
import com.jdev.course.model.Course;
import com.jdev.course.model.CurriculumWith8Semesters;
import com.jdev.course.model.DTO.*;
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
import java.util.*;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CurriculumWith8SemestersRepository curriculumRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private RequestCoursesForStudentPublisher coursesForStudentPublisher;

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
            this.sendCourseForStudent(courseSaved);
            return courseSaved;
        } else {
            throw new CourseAlreadyExistsException();
        }
    }

    private void addCurriculumToTheCourse(Course course) {
        CurriculumWith8Semesters curriculum = CurriculumWith8Semesters.builder()
                .semester1(new ArrayList<>())
                .semester2(new ArrayList<>())
                .course_id(course)
                .build();
        curriculumRepository.save(curriculum);
    }

    private Course modelingNewCourseForSave(CourseCreateDTO CourseDTO) {
        Course course = Course.builder()
                .name(CourseDTO.name())
                .created(LocalDate.now())
                .quantityDisciplines(0)
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

    public void addDisciplineUnit(Course course) {
        course.setQuantityDisciplines(course.getQuantityDisciplines() + 1);
        courseRepository.save(course);
    }

    public void sendCourseForStudent(Course course) {
        DataCourseForSendToTheStudentDTO dataCourseForSend = new DataCourseForSendToTheStudentDTO(
                course.getName(),
                course.getRegistration()
        );
        try {
            coursesForStudentPublisher.sendCourse(dataCourseForSend);
        } catch (Exception ex) {
            throw new ErrorSendCourseForStudentException(ex.getMessage());
        }
    }

    //---------------------- need urgent refactoring ----------------------
    public void removeDisciplineToTheCourse(RemoveDisciplineToTheCourseDTO data) {
        Discipline discipline = disciplineRepository.findByRegistration(data.registerDiscipline())
                .orElseThrow(DisciplineNotFoundException::new);

        Course course = this.findByCourseWithRegistration(data.registerCourse());

        CurriculumWith8Semesters curriculum = curriculumRepository.findById(course.getCurriculum().getId())
                .orElseThrow(RuntimeException::new);

        if (data.semester() == SemesterEnum.PRIMEIRO) {
            List<Discipline> semester1 = curriculum.getSemester1();
            semester1.remove(discipline);
            curriculum.setSemester1(semester1);
            curriculumRepository.save(curriculum);

            List<CurriculumWith8Semesters> curriculumSemester1 = discipline.getCurriculum_id_semester1();
            curriculumSemester1.remove(curriculum);
            disciplineRepository.save(discipline);

        } else if (data.semester() == SemesterEnum.SEGUNDO) {
            List<Discipline> semester2 = curriculum.getSemester2();
            semester2.remove(discipline);
            curriculum.setSemester2(semester2);
            curriculumRepository.save(curriculum);

            List<CurriculumWith8Semesters> curriculumSemester2 = discipline.getCurriculum_id_semester2();
            curriculumSemester2.remove(curriculum);
            disciplineRepository.save(discipline);

        } else if (data.semester() == SemesterEnum.TERCEIRO) {
            List<Discipline> semester3 = curriculum.getSemester3();
            semester3.remove(discipline);
            curriculum.setSemester3(semester3);
            curriculumRepository.save(curriculum);

            List<CurriculumWith8Semesters> curriculumIdSemester3 = discipline.getCurriculum_id_semester3();
            curriculumIdSemester3.remove(curriculum);
            disciplineRepository.save(discipline);

        } else if (data.semester() == SemesterEnum.QUARTO) {
            List<Discipline> semester4 = curriculum.getSemester4();
            semester4.remove(discipline);
            curriculum.setSemester4(semester4);
            curriculumRepository.save(curriculum);

            List<CurriculumWith8Semesters> curriculumIdSemester4 = discipline.getCurriculum_id_semester4();
            curriculumIdSemester4.remove(curriculum);
            disciplineRepository.save(discipline);

        } else if (data.semester() == SemesterEnum.QUINTO) {
            List<Discipline> semester5 = curriculum.getSemester5();
            semester5.remove(discipline);
            curriculum.setSemester5(semester5);
            curriculumRepository.save(curriculum);

            List<CurriculumWith8Semesters> curriculumIdSemester5 = discipline.getCurriculum_id_semester5();
            curriculumIdSemester5.remove(curriculum);
            disciplineRepository.save(discipline);

        } else if (data.semester() == SemesterEnum.SEXTO) {
            List<Discipline> semester6 = curriculum.getSemester6();
            semester6.remove(discipline);
            curriculum.setSemester6(semester6);
            curriculumRepository.save(curriculum);

            List<CurriculumWith8Semesters> curriculumIdSemester6 = discipline.getCurriculum_id_semester6();
            curriculumIdSemester6.remove(curriculum);
            disciplineRepository.save(discipline);

        } else if (data.semester() == SemesterEnum.SETIMO) {
            List<Discipline> semester7 = curriculum.getSemester7();
            semester7.remove(discipline);
            curriculum.setSemester7(semester7);
            curriculumRepository.save(curriculum);

            List<CurriculumWith8Semesters> curriculumIdSemester7 = discipline.getCurriculum_id_semester7();
            curriculumIdSemester7.remove(curriculum);
            disciplineRepository.save(discipline);

        } else if (data.semester() == SemesterEnum.OITAVO) {
            List<Discipline> semester8 = curriculum.getSemester8();
            semester8.remove(discipline);
            curriculum.setSemester8(semester8);
            curriculumRepository.save(curriculum);

            List<CurriculumWith8Semesters> curriculumIdSemester8 = discipline.getCurriculum_id_semester8();
            curriculumIdSemester8.remove(curriculum);
            disciplineRepository.save(discipline);
        }
    }
    //---------------------- need urgent refactoring ----------------------

    //---------------------- need urgent refactoring ----------------------
    public void addDisciplineToTheCourse(AddDisciplineToTheCourseDTO data) {
        Discipline discipline = disciplineRepository.findByRegistration(data.registerDiscipline())
                .orElseThrow(DisciplineNotFoundException::new);

        Course course = this.findByCourseWithRegistration(data.registerCourse());

        CurriculumWith8Semesters curriculum = curriculumRepository.findById(course.getCurriculum().getId())
                .orElseThrow(RuntimeException::new);

        if (data.semester() == SemesterEnum.PRIMEIRO) {
            List<Discipline> semester1 = curriculum.getSemester1();
            semester1.add(discipline);
            curriculum.setSemester1(semester1);
            CurriculumWith8Semesters curriculumSaved = curriculumRepository.save(curriculum);

            discipline.getCurriculum_id_semester1().add(curriculumSaved); //erro
            disciplineRepository.save(discipline);

        } else if (data.semester() == SemesterEnum.SEGUNDO) {
            List<Discipline> semester2 = curriculum.getSemester2();
            semester2.add(discipline);
            curriculum.setSemester2(semester2);
            CurriculumWith8Semesters curriculumSaved = curriculumRepository.save(curriculum);

            discipline.getCurriculum_id_semester2().add(curriculumSaved);
            disciplineRepository.save(discipline);

        } else if (data.semester() == SemesterEnum.TERCEIRO) {
            List<Discipline> semester3 = curriculum.getSemester3();
            semester3.add(discipline);
            curriculum.setSemester3(semester3);
            CurriculumWith8Semesters curriculumSaved = curriculumRepository.save(curriculum);

            discipline.getCurriculum_id_semester3().add(curriculumSaved);
            disciplineRepository.save(discipline);

        } else if (data.semester() == SemesterEnum.QUARTO) {
            List<Discipline> semester4 = curriculum.getSemester4();
            semester4.add(discipline);
            curriculum.setSemester4(semester4);
            CurriculumWith8Semesters curriculumSaved = curriculumRepository.save(curriculum);

            discipline.getCurriculum_id_semester4().add(curriculumSaved);
            disciplineRepository.save(discipline);

        } else if (data.semester() == SemesterEnum.QUINTO) {
            List<Discipline> semester5 = curriculum.getSemester5();
            semester5.add(discipline);
            curriculum.setSemester5(semester5);
            CurriculumWith8Semesters curriculumSaved = curriculumRepository.save(curriculum);

            discipline.getCurriculum_id_semester5().add(curriculumSaved);
            disciplineRepository.save(discipline);

        } else if (data.semester() == SemesterEnum.SEXTO) {
            List<Discipline> semester6 = curriculum.getSemester6();
            semester6.add(discipline);
            curriculum.setSemester6(semester6);
            CurriculumWith8Semesters curriculumSaved = curriculumRepository.save(curriculum);

            discipline.getCurriculum_id_semester6().add(curriculumSaved);
            disciplineRepository.save(discipline);

        } else if (data.semester() == SemesterEnum.SETIMO) {
            List<Discipline> semester7 = curriculum.getSemester7();
            semester7.add(discipline);
            curriculum.setSemester7(semester7);
            CurriculumWith8Semesters curriculumSaved = curriculumRepository.save(curriculum);

            discipline.getCurriculum_id_semester7().add(curriculumSaved);
            disciplineRepository.save(discipline);

        } else if (data.semester() == SemesterEnum.OITAVO) {
            List<Discipline> semester8 = curriculum.getSemester8();
            semester8.add(discipline);
            curriculum.setSemester8(semester8);
            CurriculumWith8Semesters curriculumSaved = curriculumRepository.save(curriculum);

            discipline.getCurriculum_id_semester8().add(curriculumSaved);
            disciplineRepository.save(discipline);
        }
        this.addDisciplineUnit(course);
    }
    //---------------------- need urgent refactoring ----------------------
}
