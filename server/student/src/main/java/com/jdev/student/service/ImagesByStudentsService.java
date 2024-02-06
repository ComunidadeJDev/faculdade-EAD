package com.jdev.student.service;

import com.jdev.student.model.Student;
import com.jdev.student.model.enums.FilesType;
import com.jdev.student.repository.ImagesByStudentsRepository;
import com.jdev.student.repository.StudentRepository;
import com.jdev.student.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ImagesByStudentsService {

    @Autowired
    private ImagesByStudentsRepository imagesByStudentsRepository;

    @Autowired
    private StudentRepository studentRepository;

    public void saveImage(MultipartFile image, String username) {
        Optional<Student> student = studentRepository.findByUsername(username);
        if (student.isPresent()) {
            Student studentForSave = student.get();
//            writeFileInDirectory(image, studentForSave);
        } else {
            throw new UserNotFoundException();
        }
    }

    //preparations for save images in folder and database

//    private void writeFileInDirectory(MultipartFile image, Student student) {
//        try {
//            byte[] bytes = image.getBytes();
//            String newFileName
//        }
//    }
}
