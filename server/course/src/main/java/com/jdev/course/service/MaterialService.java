package com.jdev.course.service;

import com.jdev.course.exceptions.CusmotomizeException.FileErrorException;
import com.jdev.course.exceptions.CusmotomizeException.FileNullContentException;
import com.jdev.course.exceptions.CusmotomizeException.InvalidFileFormatException;
import com.jdev.course.exceptions.CusmotomizeException.MaterialNotFoundException;
import com.jdev.course.model.DTO.CreateMaterialDTO;
import com.jdev.course.model.DTO.MaterialUpdateDTO;
import com.jdev.course.model.Module;
import com.jdev.course.model.enums.MaterialTypeEnum;
import com.jdev.course.model.materials.Material;
import com.jdev.course.repository.MaterialRepository;
import com.jdev.course.repository.ModuleRepository;
import com.jdev.course.utils.FileTypeCheck;
import com.jdev.course.utils.GenerateNewName;
import com.jdev.course.utils.GenerateRegister;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private VideoMaterialsService videoMaterialsService;

    @Autowired
    private CourseService courseService;

    @Value("${material-module}")
    private String materialPath;

    public List<Material> findAllMaterials() {
        return materialRepository.findAll();
    }

    public List<Material> findAllActiveMaterials() {
        return materialRepository.findAllActiveMaterials(true);
    }

    public void createMaterial(CreateMaterialDTO materialDTO) {
        Module module = moduleService.findByModuleWithRegistration(materialDTO.registrationModule());

        if (!materialDTO.file().isEmpty()) {
            if (FileTypeCheck.verifyIfIsAImage(materialDTO.file())) {
                preparingToSaveImageMaterial(materialDTO, module);
            } else if (FileTypeCheck.verifyIfIsAPdf(materialDTO.file())) {
                preparingToSavePdfMaterial(materialDTO, module);
            } else if (FileTypeCheck.verifyIfIsAVideo(materialDTO.file())) {
                preparingToSaveVideoMaterial(materialDTO, module);
            } else {
                throw new InvalidFileFormatException();
            }
        } else {
            throw new FileNullContentException();
        }
    }

    public void updateMaterial(MaterialUpdateDTO materialUpdateDTO) {
        Material material = this.findByRegister(materialUpdateDTO.materialRegister());
        material.setName(materialUpdateDTO.name());
        materialRepository.save(material);
    }

    public void setWithNotActive(String registration) {
        Material material = this.findByRegister(registration);
        material.setActive(false);
        materialRepository.save(material);
    }

    public Material findByRegister(String register) {
        Optional<Material> material = materialRepository.findByRegister(register);
        return material.orElseThrow(MaterialNotFoundException::new);
    }

    private void preparingToSaveVideoMaterial(CreateMaterialDTO materialDTO, Module module) {
        String fileName = writeFileInDirectory(materialDTO, module);

        Material material = Material.builder()
                .name(materialDTO.name())
                .register(this.generateRegister())
                .reference(fileName)
                .type(MaterialTypeEnum.VIDEO)
                .module_id(module)
                .active(true)
                .build();
        materialRepository.save(material);
        this.addMaterialInCourse(material);
    }

    private void preparingToSavePdfMaterial(CreateMaterialDTO materialDTO, Module module) {
        String fileName = writeFileInDirectory(materialDTO, module);

        Material material = Material.builder()
                .name(materialDTO.name())
                .register(this.generateRegister())
                .reference(fileName)
                .type(MaterialTypeEnum.PDF)
                .module_id(module)
                .active(true)
                .build();
        materialRepository.save(material);
        this.addMaterialInCourse(material);
    }

    private void preparingToSaveImageMaterial(CreateMaterialDTO materialDTO, Module module) {
        String fileName = writeFileInDirectory(materialDTO, module);

        Material material = Material.builder()
                .name(materialDTO.name())
                .register(this.generateRegister())
                .reference(fileName)
                .type(MaterialTypeEnum.IMAGE)
                .module_id(module)
                .active(true)
                .build();
        materialRepository.save(material);
        this.addMaterialInCourse(material);
    }

    private void addMaterialInCourse(Material material) {
        courseService.addMaterialUnit(material.getModule_id().getId_course());
    }

    private String writeFileInDirectory(CreateMaterialDTO materialDTO, Module module) {
        try {
            byte[] bytes = materialDTO.file().getBytes();
            String newFileName = this.generateFileName(materialDTO, module);
            Path path = Paths.get(materialPath + "/" + newFileName);
            Files.write(path, bytes);
            return newFileName;
        } catch (Exception ex) {
            throw new FileErrorException(ex.getMessage());
        }
    }

    private String generateRegister() {
        String newFileName = GenerateRegister.newRegister();
        if (materialRepository.findByRegister(newFileName).isEmpty()) {
            return newFileName;
        } else {
            return GenerateNewName.addCharactersToFileName(newFileName);
        }
    }

    private String generateFileName(CreateMaterialDTO materialDTO, Module module) {
        String newFileName = GenerateNewName.generateNewFileName(materialDTO.file());
        if (moduleRepository.findByName(newFileName).isEmpty()) {
            return newFileName;
        } else {
            return GenerateNewName.addCharactersToFileName(newFileName);
        }
    }


}
