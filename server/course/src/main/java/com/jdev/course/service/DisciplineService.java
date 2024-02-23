package com.jdev.course.service;

import com.jdev.course.exceptions.CusmotomizeException.CourseErrorException;
import com.jdev.course.exceptions.CusmotomizeException.CourseNotFoundException;
import com.jdev.course.exceptions.CusmotomizeException.DisciplineAlreadyExistsException;
import com.jdev.course.exceptions.CusmotomizeException.DisciplineNotFoundException;
import com.jdev.course.model.Course;
import com.jdev.course.model.DTO.DisciplineCreateDTO;
import com.jdev.course.model.DTO.DisciplineUpdateDTO;
import com.jdev.course.model.Discipline;
import com.jdev.course.repository.DisciplineRepository;
import com.jdev.course.utils.GenerateRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private CourseService courseService;

    public List<Discipline> findAllModules() {
        return disciplineRepository.findAll();
    }

    public List<Discipline> findAllActiveCourses() {
        return disciplineRepository.findAllActiveCourses(true);
    }

    public Discipline create(DisciplineCreateDTO moduleDTO) {
        if (disciplineRepository.findByName(moduleDTO.name()).isEmpty()) {
            Discipline disciplineForSave = this.modelingNewModuleForSave(moduleDTO);
            Discipline discipline = this.disciplineRepository.save(disciplineForSave);
            courseService.addModulesUnit(discipline.getId_course());
            return discipline;
        } else {
            throw new DisciplineAlreadyExistsException();
        }
    }

    private Discipline modelingNewModuleForSave(DisciplineCreateDTO moduleDTO) {
        Course course = courseService.findByCourseWithRegistration(moduleDTO.registrationCourse());
        if (course != null) {
            return Discipline.builder()
                    .name(moduleDTO.name())
                    .registration(GenerateRegister.newRegister())
                    .duration(0)
                    .quantityMaterials(0)
                    .teachers(Set.of())
                    .themes(List.of())
                    .supportMaterials(List.of())
                    .materials(List.of())
                    .id_course(course)
                    .active(true)
                    .build();
        } else {
            throw new CourseNotFoundException();
        }
    }

    public Discipline update(DisciplineUpdateDTO updateDTO) {
        Optional<Discipline> module = disciplineRepository.findByRegistration(updateDTO.registration());
        if (module.isPresent()) {
            if (updateDTO.name().isBlank() || updateDTO.name().isEmpty()) {
                throw new CourseErrorException("this name not valid!");
            } else {
                //update
                module.get().setName(updateDTO.name());
                return disciplineRepository.save(module.get());
            }
        } else {
            throw new DisciplineNotFoundException();
        }
    }

    public Discipline findByModuleWithName(String name) {
        Optional<Discipline> module = disciplineRepository.findByName(name);
        return module.orElseThrow(DisciplineNotFoundException::new);
    }

    public Discipline findByModuleWithRegistration(String registration) {
        Optional<Discipline> module = disciplineRepository.findByRegistration(registration);
        return module.orElseThrow(DisciplineNotFoundException::new);
    }

    public void setWithNotActive(String registration) {
        Discipline discipline = this.findByModuleWithRegistration(registration);
        discipline.setActive(false);
        disciplineRepository.save(discipline);
    }

    public void addMaterialUnit(Discipline Discipline) {
        Discipline.setQuantityMaterials(Discipline.getQuantityMaterials() + 1);
        disciplineRepository.save(Discipline);
    }
}
