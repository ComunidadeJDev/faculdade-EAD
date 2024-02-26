package com.jdev.student.service;

import com.jdev.student.model.Curriculum;
import com.jdev.student.model.SemesterDetails;
import com.jdev.student.model.Student;
import com.jdev.student.model.enums.SemesterEnum;
import com.jdev.student.repository.CurriculumRepository;
import com.jdev.student.repository.SemesterDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurriculumService {

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private SemesterDetailsRepository semesterDetailsRepository;

    @Transactional
    public void createCurriculum(Student student) {
        Curriculum curriculum = this.modelingNewCurriculumWithSemesterDetails(student);
        Curriculum curriculumSaved = curriculumRepository.save(curriculum);
    }

    private Curriculum modelingNewCurriculumWithSemesterDetails(Student student) {
        Curriculum curriculum = Curriculum.builder()
                .semester(SemesterEnum.PRIMEIRO)
                .student_id(student)
                .build();

        List<SemesterDetails> semesterDetailsList = new ArrayList<>();

        SemesterDetails semesterDetails1 = SemesterDetails.builder()
                .curriculum1(curriculum)
                .build();
        semesterDetailsList.add(semesterDetails1);

        SemesterDetails semesterDetails2 = SemesterDetails.builder()
                .curriculum2(curriculum)
                .build();
        semesterDetailsList.add(semesterDetails2);

        SemesterDetails semesterDetails3 = SemesterDetails.builder()
                .curriculum2(curriculum)
                .build();
        semesterDetailsList.add(semesterDetails3);

//        SemesterDetails semesterDetails4 = SemesterDetails.builder()
//                .curriculum2(curriculum)
//                .build();
//        semesterDetailsList.add(semesterDetails4);
//
//        SemesterDetails semesterDetails5 = SemesterDetails.builder()
//                .curriculum2(curriculum)
//                .build();
//        semesterDetailsList.add(semesterDetails5);
//
//        SemesterDetails semesterDetails6 = SemesterDetails.builder()
//                .curriculum2(curriculum)
//                .build();
//        semesterDetailsList.add(semesterDetails6);
//
//        SemesterDetails semesterDetails7 = SemesterDetails.builder()
//                .curriculum2(curriculum)
//                .build();
//        semesterDetailsList.add(semesterDetails7);
//
//        SemesterDetails semesterDetails8 = SemesterDetails.builder()
//                .curriculum2(curriculum)
//                .build();
//        semesterDetailsList.add(semesterDetails8);

        semesterDetailsRepository.saveAll(semesterDetailsList);
        curriculum.setSemester1(semesterDetails1);
        curriculum.setSemester2(semesterDetails2);
        curriculum.setSemester3(semesterDetails3);
//        curriculum.setSemester4(semesterDetails4);
//        curriculum.setSemester5(semesterDetails5);
//        curriculum.setSemester6(semesterDetails6);
//        curriculum.setSemester7(semesterDetails7);
//        curriculum.setSemester8(semesterDetails8);

        return curriculum;
    }
}
