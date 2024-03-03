package com.jdev.student.service;

import com.jdev.student.model.Curriculum;
import com.jdev.student.model.Student;
import com.jdev.student.model.enums.SemesterEnum;
import com.jdev.student.model.externalClasses.Course;
import com.jdev.student.repository.CurriculumRepository;
import com.jdev.student.service.externalClasses.CourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurriculumService {

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private CourseService courseService;

    @Transactional
    public void createCurriculum(Student student) {
        Curriculum curriculum = this.modelingNewCurriculumWithSemesterDetails(student);
        curriculumRepository.save(curriculum);
    }

    private Curriculum modelingNewCurriculumWithSemesterDetails(Student student) {
        Course course = this.courseService.findByRegistration(student.getRegisterCourse());
        return Curriculum.builder()
                .semester(SemesterEnum.PRIMEIRO)
                .course(course)
                .student_id(student)
                .build();
    }
}
