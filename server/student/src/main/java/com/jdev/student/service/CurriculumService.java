package com.jdev.student.service;

import com.jdev.student.model.Curriculum;
import com.jdev.student.model.Student;
import com.jdev.student.model.enums.SemesterEnum;
import com.jdev.student.repository.CurriculumRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurriculumService {

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Transactional
    public void createCurriculum(Student student) {
        Curriculum curriculum = this.modelingNewCurriculumWithSemesterDetails(student);
        curriculumRepository.save(curriculum);
    }

    private Curriculum modelingNewCurriculumWithSemesterDetails(Student student) {
        return Curriculum.builder()
                .semester(SemesterEnum.PRIMEIRO)
                .student_id(student)
                .build();
    }
}
