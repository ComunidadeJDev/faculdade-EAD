package com.jdev.course.service;

import com.jdev.course.repository.CourseRepository;
import com.jdev.course.repository.PdfMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PdfMaterialsService {

    @Value("${pdf-material-path}")
    private String pdfMaterialPath;

    @Autowired
    private PdfMaterialRepository pdfMaterialRepository;

    @Autowired
    private CourseRepository courseRepository;

//    public void create(PdfMaterialForCreate file, String username) {
//        if (FileTypeCheck.verifyIfIsAFile(file.file())) {
//            Optional<Course>
//        }
//    }


}
