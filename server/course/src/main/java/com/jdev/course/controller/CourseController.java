package com.jdev.course.controller;

import com.jdev.course.model.Course;
import com.jdev.course.model.DTO.*;
import com.jdev.course.model.Module;
import com.jdev.course.model.enums.MaterialTypeEnum;
import com.jdev.course.model.materials.Material;
import com.jdev.course.service.CourseService;
import com.jdev.course.service.MaterialService;
import com.jdev.course.service.ModuleService;
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
    private ModuleService moduleService;

    @Autowired
    private MaterialService materialService;

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

    //------------------------------------------- Module -------------------------------------------

    @GetMapping("/module/list")
    public ResponseEntity<List<Module>> findAllModules() {
        return ResponseEntity.ok().body(moduleService.findAllModules());
    }

    @GetMapping("module/list/active")
    public ResponseEntity<List<Module>> findAllActiveModules() {
        return ResponseEntity.ok().body(moduleService.findAllActiveCourses());
    }

    @PostMapping("/module/create")
    public ResponseEntity<Module> createModule(@RequestBody ModuleCreateDTO moduleDTO) {
        return ResponseEntity.ok().body(moduleService.create(moduleDTO));
    }

    @GetMapping("/module/list/{registration}")
    public ResponseEntity<Module> findModuleByRegistration(@PathVariable String registration) {
        return ResponseEntity.ok().body(moduleService.findByModuleWithRegistration(registration));
    }

    @PutMapping("/module/update")
    public ResponseEntity<Module> updateModule(@RequestBody ModuleUpdateDTO updateDTO) {
        return ResponseEntity.ok().body(moduleService.update(updateDTO));
    }

    @DeleteMapping("/module/disable/{registration}")
    public ResponseEntity<Object> disableModule(@PathVariable String registration) {
        moduleService.setWithNotActive(registration);
        return ResponseEntity.noContent().build();
    }

    //------------------------------------------- Material -------------------------------------------

    @GetMapping("/material/list")
    public ResponseEntity<List<Material>> findAllMaterials() {
        return ResponseEntity.ok().body(materialService.findAllMaterials());
    }

    @PostMapping("/material/create")
    public ResponseEntity<Object> createMaterial(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("registrationModule") String registrationModule) {
        CreateMaterialDTO materialCreateDTO = new CreateMaterialDTO(name, file, registrationModule);
        materialService.createMaterial(materialCreateDTO);
        return ResponseEntity.noContent().build();
    }
}
