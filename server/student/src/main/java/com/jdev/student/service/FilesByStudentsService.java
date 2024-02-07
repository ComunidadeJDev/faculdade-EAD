package com.jdev.student.service;

import com.jdev.student.model.FilesAndImages.FilesByStudents;
import com.jdev.student.model.Student;
import com.jdev.student.model.enums.FilesTypeEnum;
import com.jdev.student.repository.FilesByStudentsRepository;
import com.jdev.student.repository.StudentRepository;
import com.jdev.student.service.exceptions.UserNotFoundException;
import com.jdev.student.utils.GenerateNewFileName;
import com.jdev.student.utils.GenerateRegister;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class FilesByStudentsService {

    @Autowired
    private FilesByStudentsRepository filesByStudentsRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Value("${files-students-path}")
    private String filesImages;

    public void saveFile(MultipartFile file, String username, FilesTypeEnum fileType) {
        Optional<Student> student = studentRepository.findByUsername(username);
        if (student.isPresent()) {
            Student studentForSave = student.get();
            writeFileInDirectory(file, studentForSave, fileType);
        } else {
            throw new UserNotFoundException();
        }
    }

    //preparations for save file in folder and database

    private void writeFileInDirectory(MultipartFile file, Student student, FilesTypeEnum fileType) {
        try {
            byte[] bytes = file.getBytes();
            String newFileName = generateFileName(file, student);
            Path path = Paths.get(filesImages + "/" + newFileName);
            Files.write(path, bytes);
            saveFileReferenceInDatabase(newFileName, student, fileType);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void saveFileReferenceInDatabase(String newFileName, Student student, FilesTypeEnum fileType) {
        String register = GenerateRegister.newRegister();
        FilesByStudents file = null;

        if (Objects.equals(fileType, FilesTypeEnum.CPF)) {
            file = new FilesByStudents(newFileName, register, student, null, null);
        } else if (Objects.equals(fileType.name(), "RG")) {
            file = new FilesByStudents(newFileName, register, null, student, null);
        } else if (Objects.equals(fileType.name(), "COMPLETION")) {
            file = new FilesByStudents(newFileName, register, null, null, student);
        }
        filesByStudentsRepository.save(file);
    }

    private String generateFileName(MultipartFile file, Student student) {
        String newFileName = GenerateNewFileName.generateFileName(file, student);
        if (filesByStudentsRepository.findByReference(newFileName).isEmpty()) {
            return newFileName;
        } else {
            return GenerateNewFileName.addCharactersToFileName(newFileName);
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
