package com.jdev.student.service;

import com.jdev.student.model.FilesAndImages.FilesByStudents;
import com.jdev.student.model.FilesAndImages.ImagesByStudents;
import com.jdev.student.model.Student;
import com.jdev.student.repository.ImagesByStudentsRepository;
import com.jdev.student.repository.StudentRepository;
import com.jdev.student.service.exceptions.IOException;
import com.jdev.student.service.exceptions.ImageNotFoundException;
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
import java.util.Optional;

@Service
@Transactional
public class ImagesByStudentsService {

    @Autowired
    private ImagesByStudentsRepository imagesByStudentsRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Value("${images-students-path}")
    private String pathImages;

    public void saveImage(MultipartFile image, String username) {
        Optional<Student> student = studentRepository.findByUsername(username);
        if (student.isPresent()) {
            Student studentForSave = student.get();
            writeFileInDirectory(image, studentForSave);
        } else {
            throw new UserNotFoundException();
        }
    }

//    preparations for save images in folder and database

    private void writeFileInDirectory(MultipartFile image, Student student) {
        try {
            byte[] bytes = image.getBytes();
            String newFileName = this.generateFileName(image, student);
            Path path = Paths.get(pathImages + "/" + newFileName);
            Files.write(path, bytes);
            saveFilesReferenceInDatabase(newFileName, student);
        } catch (Exception ex) {
            throw new IOException();
        }
    }

    private void saveFilesReferenceInDatabase(String fileName, Student student) {
        String register = GenerateRegister.newRegister();
        ImagesByStudents image = new ImagesByStudents(fileName, register, student);
        imagesByStudentsRepository.save(image);
    }

    private String generateFileName(MultipartFile file, Student student) {
        String newFileName = GenerateNewFileName.generateFileName(file, student);
        if (imagesByStudentsRepository.findByReference(newFileName).isEmpty()) {
            return newFileName;
        } else {
            return GenerateNewFileName.addCharactersToFileName(newFileName);
        }
    }

    //ADM

    public List<ImagesByStudents> findAll() {
        return imagesByStudentsRepository.findAll();
    }

    public ImagesByStudents findByReference(String reference) {
        Optional<ImagesByStudents> image = imagesByStudentsRepository.findByReference(reference);
        return image.orElseThrow(ImageNotFoundException::new);
    }

    public void deleteByReference(String reference) {
        ImagesByStudents image = this.findByReference(reference);
        try {
            Path path = Paths.get(pathImages + "/" + image.getReference());
            Files.delete(path);
            imagesByStudentsRepository.delete(image);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
