package com.jdev.course.service;

import com.jdev.course.model.Course;
import com.jdev.course.model.CurriculumWith8Semesters;
import com.jdev.course.repository.CurriculumWith8SemestersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurriculumWith8SemestersService {

    @Autowired
    private CurriculumWith8SemestersRepository curriculumRepository;

    public List<CurriculumWith8Semesters> findAll() {
        return curriculumRepository.findAll();
    }

//    public CurriculumWith8Semesters createCurriculum() {
//        CurriculumWith8Semesters curriculum = modelingNewCurriculumForSave();
//        return curriculumRepository.save(curriculum);
//    }

//    private CurriculumWith8Semesters modelingNewCurriculumForSave() {
//        return CurriculumWith8Semesters.builder()
//                .build();
//    }

//    public void setCourseIdInCurriculum(Course course) {
//        Optional<CurriculumWith8Semesters> curriculum = curriculumRepository.findById(course.getCurriculum().getId());
//        if (curriculum.isPresent()) {
//            curriculum.get().setCourse_id(course);
//            curriculumRepository.save(curriculum.get());
//        } else {
//            throw new RuntimeException("erro ao criar curriculum");
//        }
//    }
}
