package com.jdev.course.service;

import com.jdev.course.exceptions.CusmotomizeException.FileErrorException;
import com.jdev.course.exceptions.CusmotomizeException.FileNullContentException;
import com.jdev.course.exceptions.CusmotomizeException.InvalidFileFormatException;
import com.jdev.course.exceptions.CusmotomizeException.MaterialNotFoundException;
import com.jdev.course.model.DTO.CreateMaterialDTO;
import com.jdev.course.model.DTO.MaterialUpdateDTO;
import com.jdev.course.model.Discipline;
import com.jdev.course.model.enums.MaterialTypeEnum;
import com.jdev.course.model.materials.Material;
import com.jdev.course.repository.MaterialRepository;
import com.jdev.course.repository.DisciplineRepository;
import com.jdev.course.utils.FileTypeCheck;
import com.jdev.course.utils.GenerateNewName;
import com.jdev.course.utils.GenerateRegister;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private DisciplineService disciplineService;

    @Autowired
    private DisciplineRepository disciplineRepository;

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
        Discipline discipline = disciplineService.findByDisciplineWithRegistration(materialDTO.registrationDiscipline());

        if (!materialDTO.file().isEmpty()) {
            if (FileTypeCheck.verifyIfIsAImage(materialDTO.file())) {
                preparingToSaveImageMaterial(materialDTO, discipline);
            } else if (FileTypeCheck.verifyIfIsAPdf(materialDTO.file())) {
                preparingToSavePdfMaterial(materialDTO, discipline);
            } else if (FileTypeCheck.verifyIfIsAVideo(materialDTO.file())) {
                preparingToSaveVideoMaterial(materialDTO, discipline);
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

    private void preparingToSaveVideoMaterial(CreateMaterialDTO materialDTO, Discipline discipline) {
        String fileName = writeFileInDirectory(materialDTO, discipline);

        Material material = Material.builder()
                .name(materialDTO.name())
                .register(this.generateRegister())
                .reference(fileName)
                .type(MaterialTypeEnum.VIDEO)
                .discipline_id(discipline)
                .active(true)
                .build();
        materialRepository.save(material);
        disciplineService.addMaterialUnit(discipline);
    }

    private void preparingToSavePdfMaterial(CreateMaterialDTO materialDTO, Discipline discipline) {
        String fileName = writeFileInDirectory(materialDTO, discipline);

        Material material = Material.builder()
                .name(materialDTO.name())
                .register(this.generateRegister())
                .reference(fileName)
                .type(MaterialTypeEnum.PDF)
                .discipline_id(discipline)
                .active(true)
                .build();
        materialRepository.save(material);
        disciplineService.addMaterialUnit(discipline);
    }

    private void preparingToSaveImageMaterial(CreateMaterialDTO materialDTO, Discipline discipline) {
        String fileName = writeFileInDirectory(materialDTO, discipline);

        Material material = Material.builder()
                .name(materialDTO.name())
                .register(this.generateRegister())
                .reference(fileName)
                .type(MaterialTypeEnum.IMAGE)
                .discipline_id(discipline)
                .active(true)
                .build();
        materialRepository.save(material);
        disciplineService.addMaterialUnit(discipline);
    }


    private String writeFileInDirectory(CreateMaterialDTO materialDTO, Discipline discipline) {
        try {
            byte[] bytes = materialDTO.file().getBytes();
            String newFileName = this.generateFileName(materialDTO, discipline);
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

    private String generateFileName(CreateMaterialDTO materialDTO, Discipline discipline) {
        String newFileName = GenerateNewName.generateNewFileName(materialDTO.file());
        if (disciplineRepository.findByName(newFileName).isEmpty()) {
            return newFileName;
        } else {
            return GenerateNewName.addCharactersToFileName(newFileName);
        }
    }


}
