package com.jdev.student.service;

import com.jdev.student.model.Materials.FilesByStudents;
import com.jdev.student.model.Student;
import com.jdev.student.model.enums.FilesType;
import com.jdev.student.repository.FilesByStudentsRepository;
import com.jdev.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilesByStudentsService {

    @Autowired
    private FilesByStudentsRepository filesByStudentsRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Value("${images-students-path}")
    private String pathImages;

    public void saveFile(MultipartFile file, String username, FilesType fileType) {
        Optional<Student> student = studentRepository.findByUsername(username);
        if (student.isPresent()) {
            Student studentForSave = student.get();
            writeFileInDirectory(file, studentForSave, fileType);
        } else {
            throw new RuntimeException("Student Not found!");
        }
    }

    //preparations for save file in folder and database

    private void writeFileInDirectory(MultipartFile file, Student student, FilesType fileType) {
        try {
            byte[] bytes = file.getBytes();
            String newFileName = generateNewFileName(file, student);
            Path path = Paths.get(pathImages + "/" + newFileName);
            Files.write(path, bytes);
            saveFileReferenceInDatabase(newFileName, student, fileType);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void saveFileReferenceInDatabase(String newFileName, Student student, FilesType fileType) {
        String register = generateRegister();
        FilesByStudents file = null;

        if (Objects.equals(fileType, FilesType.CPF)) {
            file = new FilesByStudents(newFileName, register, student, null, null);
        } else if (Objects.equals(fileType.name(), "RG")) {
            file = new FilesByStudents(newFileName, register, null, student, null);
        } else if (Objects.equals(fileType.name(), "COMPLETION")) {
            file = new FilesByStudents(newFileName, register, null, null, student);
        }
        filesByStudentsRepository.save(file);
    }

    private String generateRegister() {
        return UUID.randomUUID().toString().substring(0,10);
    }

    private String generateNewFileName(MultipartFile file, Student student) {
        String randomId = generateRandomId();
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String newFileName = student.getUsername() + "_" + randomId + fileExtension;

        if (filesByStudentsRepository.findByReference(newFileName).isEmpty()) {
            return newFileName;
        } else {
            return newFileName + UUID.randomUUID().toString().substring(0, 5);
        }
    }

    private String generateRandomId() {
        return UUID.randomUUID().toString().substring(0, 30);
    }

    // admin methods

    public List<FilesByStudents> findAll() {
        return filesByStudentsRepository.findAll();
    }
}
