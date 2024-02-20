package com.jdev.course.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class GenerateNewName {

    public static String generateCodec(MultipartFile file) {
        String randomId = generateRandomId10();
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        return randomId + fileExtension;
    }

    public static String generateNewFileName(MultipartFile file) {
        String randomId = generateRandomId30();
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        return randomId + fileExtension;
    }

    public static String generateRandomId10() {
        return UUID.randomUUID().toString().substring(0, 10);
    }

    public static String generateRandomId30() {
        return UUID.randomUUID().toString().substring(0, 30);
    }

    public static String addCharactersToFileName(String fileName) {
        return fileName + UUID.randomUUID().toString().substring(0, 5);
    }
}
