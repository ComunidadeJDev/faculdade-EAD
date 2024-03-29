package com.jdev.course.controller;

import com.jdev.course.model.Course;
import com.jdev.course.model.CurriculumWith8Semesters;
import com.jdev.course.model.DTO.*;
import com.jdev.course.model.Discipline;
import com.jdev.course.model.materials.Material;
import com.jdev.course.service.CourseService;
import com.jdev.course.service.CurriculumWith8SemestersService;
import com.jdev.course.service.MaterialService;
import com.jdev.course.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private DisciplineService disciplineService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private CurriculumWith8SemestersService curriculumService;

    @GetMapping
    public String test() {
        return "connected";
    }

    //------------------------------------------- Course -------------------------------------------

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody CourseCreateDTO courseDTO) {
        return ResponseEntity.ok().body(courseService.create(courseDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Course>> findAllCourses() {
        return ResponseEntity.ok().body(courseService.findAll());
    }

    @GetMapping("/list/active")
    public ResponseEntity<List<Course>> findAllActiveCourses() {
        return ResponseEntity.ok().body(courseService.findAllActiveCourses());
    }

    @GetMapping("/list/{registration}")
    public ResponseEntity<Course> findCourseByRegistration(@PathVariable String registration) {
        return ResponseEntity.ok().body(courseService.findByCourseWithRegistration(registration));
    }

    @PutMapping("/update")
    public ResponseEntity<Course> updateCourse(@RequestBody CourseUpdateDTO updateDTO) {
        return ResponseEntity.ok().body(courseService.update(updateDTO));
    }

    @DeleteMapping("/disable/{registration}")
    public ResponseEntity<Object> disableCourse(@PathVariable String registration) {
        courseService.setWithNotActive(registration);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add/discipline")
    public ResponseEntity<Object> addDisciplineToTheCourse(@RequestBody AddDisciplineToTheCourseDTO data) {
        courseService.addDisciplineToTheCourse(data);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/remove/discipline")
    public ResponseEntity<Object> removeDisciplineToTheCourse(@RequestBody RemoveDisciplineToTheCourseDTO data) {
        courseService.removeDisciplineToTheCourse(data);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/curriculum/list")
    public ResponseEntity<List<CurriculumWith8Semesters>> findAllCurriculums() {
        return ResponseEntity.ok().body(curriculumService.findAll());
    }

    //------------------------------------------- Discipline -------------------------------------------

    @GetMapping("/discipline/list")
    public ResponseEntity<List<Discipline>> findAllDisciplines() {
        return ResponseEntity.ok().body(disciplineService.findAllDisciplines());
    }

    @GetMapping("discipline/list/active")
    public ResponseEntity<List<Discipline>> findAllActiveDisciplines() {
        return ResponseEntity.ok().body(disciplineService.findAllActiveDisciplines());
    }

    @PostMapping("/discipline/create")
    public ResponseEntity<Discipline> createDiscipline(@RequestBody DisciplineCreateDTO moduleDTO) {
        return ResponseEntity.ok().body(disciplineService.create(moduleDTO));
    }

    @GetMapping("/discipline/list/{registration}")
    public ResponseEntity<Discipline> findDisciplineByRegistration(@PathVariable String registration) {
        return ResponseEntity.ok().body(disciplineService.findByDisciplineWithRegistration(registration));
    }

    @PutMapping("/discipline/update")
    public ResponseEntity<Discipline> updateDiscipline(@RequestBody DisciplineUpdateDTO updateDTO) {
        return ResponseEntity.ok().body(disciplineService.update(updateDTO));
    }

    @DeleteMapping("/discipline/disable/{registration}")
    public ResponseEntity<Object> disableDiscipline(@PathVariable String registration) {
        disciplineService.setAsNotActive(registration);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/discipline/enable/{registration}")
    public ResponseEntity<Object> enableDiscipline(@PathVariable String registration) {
        disciplineService.setAsActive(registration);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/discipline/theme/add")
    public ResponseEntity<Object> addThemeToTheDiscipline(@RequestBody AddThemeToCourseDTO data) {
        disciplineService.addThemesToTheDiscipline(data);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/discipline/theme/remove")
    public ResponseEntity<Object> removeThemeToTheDiscipline(@RequestBody RemoveThemeFromDisciplineDTO data) {
        disciplineService.removeThemeFromDiscipline(data);
        return ResponseEntity.noContent().build();
    }

    //------------------------------------------- Material -------------------------------------------

    @GetMapping("/material/list")
    public ResponseEntity<List<Material>> findAllMaterials() {
        return ResponseEntity.ok().body(materialService.findAllMaterials());
    }

    @GetMapping("/material/list/active")
    public ResponseEntity<List<Material>> findAllActiveMaterials() {
        return ResponseEntity.ok().body(materialService.findAllActiveMaterials());
    }

    @GetMapping("/material/search/{register}")
    public ResponseEntity<Material> findMaterialByRegister(@PathVariable String register) {
        return ResponseEntity.ok().body(materialService.findByRegister(register));
    }

    @PostMapping("/material/create")
    public ResponseEntity<Object> createMaterial(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("registrationDiscipline") String registrationDiscipline) {
        CreateMaterialDTO materialCreateDTO = new CreateMaterialDTO(name, file, registrationDiscipline);
        materialService.createMaterial(materialCreateDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/material/update")
    public ResponseEntity<Object> updateMaterial(@RequestBody MaterialUpdateDTO updateDTO) {
        materialService.updateMaterial(updateDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/material/disable/{register}")
    public ResponseEntity<Object> setMaterialAsNotActive(@PathVariable String register) {
        materialService.setAsNotActive(register);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/material/enable/{register}")
    public ResponseEntity<Object> setMaterialAsActive(@PathVariable String register) {
        materialService.setAsActive(register);
        return ResponseEntity.noContent().build();
    }
}
