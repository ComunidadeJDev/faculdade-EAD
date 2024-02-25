package com.jdev.course.service;

import com.jdev.course.exceptions.CusmotomizeException.CourseErrorException;
import com.jdev.course.exceptions.CusmotomizeException.DisciplineAlreadyExistsException;
import com.jdev.course.exceptions.CusmotomizeException.DisciplineNotFoundException;
import com.jdev.course.exceptions.CusmotomizeException.ThemeException;
import com.jdev.course.model.DTO.AddThemeToCourseDTO;
import com.jdev.course.model.DTO.DisciplineCreateDTO;
import com.jdev.course.model.DTO.DisciplineUpdateDTO;
import com.jdev.course.model.DTO.RemoveThemeFromDisciplineDTO;
import com.jdev.course.model.Discipline;
import com.jdev.course.model.enums.ThemesEnum;
import com.jdev.course.model.materials.Material;
import com.jdev.course.repository.DisciplineRepository;
import com.jdev.course.utils.GenerateRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private CourseService courseService;

    public List<Discipline> findAllDisciplines() {
        return disciplineRepository.findAll();
    }

    public List<Discipline> findAllActiveDisciplines() {
        return disciplineRepository.findAllActiveCourses(true);
    }

    public Discipline create(DisciplineCreateDTO moduleDTO) {
        if (disciplineRepository.findByName(moduleDTO.name()).isEmpty()) {
            Discipline disciplineForSave = this.modelingNewDisciplineForSave(moduleDTO);
            return this.disciplineRepository.save(disciplineForSave);
        } else {
            throw new DisciplineAlreadyExistsException();
        }
    }

    private Discipline modelingNewDisciplineForSave(DisciplineCreateDTO moduleDTO) {
            return Discipline.builder()
                    .name(moduleDTO.name())
                    .registration(GenerateRegister.newRegister())
                    .duration(0)
                    .quantityMaterials(0)
                    .teachers(Set.of())
                    .themes(List.of())
                    .materials(List.of())
                    .active(true)
                    .build();
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

    public void addThemesToTheDiscipline(AddThemeToCourseDTO data) {
        if (!data.themes().isEmpty()) {
            Discipline discipline = this.findByDisciplineWithRegistration(data.registerDiscipline());
            discipline.getThemes().addAll(data.themes());
            this.disciplineRepository.save(discipline);
        } else {
            throw new ThemeException("themes null content!");
        }
    }

    public void removeThemeFromDiscipline(RemoveThemeFromDisciplineDTO data) {
        if (data.theme() != null) {
            Discipline discipline = this.findByDisciplineWithRegistration(data.registerDiscipline());
            List<ThemesEnum> themeDiscipline = discipline.getThemes();
            if (themeDiscipline.contains(data.theme())) {
                discipline.getThemes().remove(data.theme());
                disciplineRepository.save(discipline);
            } else {
                throw new ThemeException("Theme not exist in discipline!");
            }
        } else {
            throw new ThemeException("themes null content!");
        }
    }

    public Discipline findByDisciplineWithName(String name) {
        Optional<Discipline> module = disciplineRepository.findByName(name);
        return module.orElseThrow(DisciplineNotFoundException::new);
    }

    public Discipline findByDisciplineWithRegistration(String registration) {
        Optional<Discipline> module = disciplineRepository.findByRegistration(registration);
        return module.orElseThrow(DisciplineNotFoundException::new);
    }

    public void setAsNotActive(String registration) {
        Discipline discipline = this.findByDisciplineWithRegistration(registration);
        if (discipline.getActive()) {
            discipline.setActive(false);
            disciplineRepository.save(discipline);
            this.setAllMaterialsAsNotActive(discipline);
        }
    }

    public void setAsActive(String registration) {
        Discipline discipline = this.findByDisciplineWithRegistration(registration);
        if (!discipline.getActive()) {
            discipline.setActive(true);
            disciplineRepository.save(discipline);
            this.setAllMaterialsAsActive(discipline);
        }
    }

    // is in this service to avoid recursion problems
    public void setAllMaterialsAsNotActive(Discipline discipline) {
        List<Material> subjectMaterials = discipline.getMaterials();
        List<Material> materialAsNotActive = new ArrayList<>();

        for(Material material : subjectMaterials) {
            material.setActive(false);
            materialAsNotActive.add(material);
        }
        discipline.setMaterials(materialAsNotActive);
        disciplineRepository.save(discipline);
    }

    public void setAllMaterialsAsActive(Discipline discipline) {
        List<Material> subjectMaterials = discipline.getMaterials();
        List<Material> ActiveMaterials = new ArrayList<>();

        for(Material material : subjectMaterials) {
            material.setActive(true);
            ActiveMaterials.add(material);
        }
        discipline.setMaterials(ActiveMaterials);
        disciplineRepository.save(discipline);
    }

    public void addMaterialUnit(Discipline discipline) {
        discipline.setQuantityMaterials(discipline.getQuantityMaterials() + 1);
        disciplineRepository.save(discipline);
    }
}
