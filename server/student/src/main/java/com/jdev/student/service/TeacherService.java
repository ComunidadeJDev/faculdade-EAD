package com.jdev.student.service;

import com.jdev.student.model.DTO.TeacherRegistrationDTO;
import com.jdev.student.model.externalClasses.Teacher;
import com.jdev.student.repository.TeacherRepository;
import com.jdev.student.utils.GenerateNewFileName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {
    @Autowired
    private  TeacherRepository teacherRepository;


public List<Teacher> findAllTeachers(){
    return teacherRepository.findAll();
}

    public Teacher createTeacher(TeacherRegistrationDTO teacherDTO){
        Teacher teacherSave = modellingNewTeacher(teacherDTO);
        Teacher save = teacherRepository.save(teacherSave);
        return save;
    }

    private Teacher modellingNewTeacher(TeacherRegistrationDTO teacher) {
        Teacher teacherForSave = new Teacher();
        teacherForSave.setCompleteNameTeacher(teacher.completeNameTeacher());
        teacherForSave.setEmailTeacher(teacher.emailTeacher());
        teacherForSave.setCpfTeacher(teacher.cpfTeacher());
        teacherForSave.setNumberHouseTeacher(teacher.numberHouseTeacher());
        teacherForSave.setAddressTeacher(teacher.addressTeacher());
        teacherForSave.setPhoneTeacher(teacher.phoneTeacher());
        teacherForSave.setEthnicityTeacher(teacher.ethnicityTeacher());
        teacherForSave.setNationalityTeacher(teacher.nationalityTeacher());
        teacherForSave.setBirthdayTeacher(teacher.birthdayTeacher());
        teacherForSave.setCityTeacher(teacher.cityTeacher());
        teacherForSave.setNumRegistrationTeacher(generateNumRegistrationTeacher());
        return teacherForSave;
    }

    private String generateNumRegistrationTeacher() {
        String registration = GenerateNewFileName.generateRandomId();
        Boolean confirm = findByRegistrationForGenerateRegistration(registration);
        if (confirm != null) {
            return registration;
        } else {
            return registration + UUID.randomUUID().toString().substring(0, 5);
        }
    }

    private Boolean findByRegistrationForGenerateRegistration(String registration) {
        return null;
    }
}
