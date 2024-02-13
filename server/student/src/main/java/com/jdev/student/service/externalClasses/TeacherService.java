package com.jdev.student.service.externalClasses;

import com.jdev.student.model.DTO.TeacherRegistrationDTO;
import com.jdev.student.model.externalClasses.Module;
import com.jdev.student.model.externalClasses.Teacher;
import com.jdev.student.repository.TeacherRepository;
import com.jdev.student.utils.GenerateNewFileName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ModuleService moduleService;


    public List<Teacher> findAllTeachers(){
        return teacherRepository.findAll();
    }

    public Teacher create(TeacherRegistrationDTO teacherDTO){
        Teacher teacherSave = modellingNewTeacher(teacherDTO);
        return teacherRepository.save(teacherSave);
    }

    private Teacher modellingNewTeacher(TeacherRegistrationDTO teacher) {
        Teacher teacherForSave = new Teacher();
        Module module = moduleService.findByName(teacher.modelName());
        teacherForSave.setCompleteNameTeacher(teacher.completeNameTeacher());
        teacherForSave.setModule_id(module);
        return teacherForSave;
    }
}
