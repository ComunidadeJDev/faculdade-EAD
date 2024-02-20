package com.jdev.course.service;

import com.jdev.course.exceptions.CusmotomizeException.FileErrorException;
import com.jdev.course.exceptions.CusmotomizeException.FileNullContentException;
import com.jdev.course.model.DTO.CreateMaterialDTO;
import com.jdev.course.model.Module;
import com.jdev.course.model.enums.MaterialTypeEnum;
import com.jdev.course.model.materials.Material;
import com.jdev.course.repository.MaterialRepository;
import com.jdev.course.repository.ModuleRepository;
import com.jdev.course.utils.FileTypeCheck;
import com.jdev.course.utils.GenerateNewName;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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

    @Value("${material-module}")
    private String materialPath;

    public List<Material> findAllMaterials() {
        return materialRepository.findAll();
    }

    public void createMaterial(CreateMaterialDTO materialDTO) {
        Module module = moduleService.findByModuleWithRegistration(materialDTO.registrationModule());

        if (!materialDTO.file().isEmpty()) {
            if (FileTypeCheck.verifyIfIsAImage(materialDTO.file())) {
                preparingToSaveImageMaterial(materialDTO, module);
            }
            if (FileTypeCheck.verifyIfIsAPdf(materialDTO.file())) {
                preparingToSavePdfMaterial(materialDTO, module);
            }
            if (FileTypeCheck.verifyIfIsAVideo(materialDTO.file())) {
                preparingToSaveVideoMaterial(materialDTO, module);
            }
            // incluir erro de tipo de arquivo
        } else {
            throw new FileNullContentException();
        }
    }

    private void preparingToSaveVideoMaterial(CreateMaterialDTO materialDTO, Module module) {
        String fileName = writeFileInDirectory(materialDTO, module);

        Material material = Material.builder()
                .name(materialDTO.name())
                .register(this.generateRegister())
                .reference(fileName)
                .type(MaterialTypeEnum.VIDEO)
                .module_id(Set.of(module))
                .active(true)
                .build();
        materialRepository.save(material);
    }

    private void preparingToSavePdfMaterial(CreateMaterialDTO materialDTO, Module module) {
        String fileName = writeFileInDirectory(materialDTO, module);

        Material material = Material.builder()
                .name(materialDTO.name())
                .register(this.generateRegister())
                .reference(fileName)
                .type(MaterialTypeEnum.PDF)
                .module_id(Set.of(module))
                .active(true)
                .build();
        materialRepository.save(material);
    }

    private void preparingToSaveImageMaterial(CreateMaterialDTO materialDTO, Module module) {
        String fileName = writeFileInDirectory(materialDTO, module);

        Material material = Material.builder()
                .name(materialDTO.name())
                .register(this.generateRegister())
                .reference(fileName)
                .type(MaterialTypeEnum.IMAGE)
                .module_id(Set.of(module))
                .active(true)
                .build();
        materialRepository.save(material);
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
        String newFileName = GenerateNewName.generateRandomId10();
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
