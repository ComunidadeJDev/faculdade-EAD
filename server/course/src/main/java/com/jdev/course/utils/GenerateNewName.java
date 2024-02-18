package com.jdev.course.utils;

import java.util.UUID;

public class GenerateNewName {

//    public static String generateFileName(MultipartFile file, Student student) {
//        String randomId = generateRandomId();
//        String originalFileName = file.getOriginalFilename();
//        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
//        return student.getUsername() + "_" + randomId + fileExtension;
//    }

    public static String generateRandomId() {
        return UUID.randomUUID().toString().substring(0, 10);
    }

    public static String addCharactersToFileName(String fileName) {
        return fileName + UUID.randomUUID().toString().substring(0, 5);
    }
}
