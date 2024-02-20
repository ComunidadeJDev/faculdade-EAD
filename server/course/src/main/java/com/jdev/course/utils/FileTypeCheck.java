package com.jdev.course.utils;

import com.jdev.course.exceptions.CusmotomizeException.FileErrorException;
import com.jdev.course.exceptions.CusmotomizeException.FileNullContentException;
import org.springframework.web.multipart.MultipartFile;

public class FileTypeCheck {

    public static Boolean verifyIfIsAImage(MultipartFile file) throws FileNullContentException {
        return isImage(file);
    }

    public static boolean verifyIfIsAPdf(MultipartFile file) throws FileNullContentException {
        return isPDF(file);
    }

    public static boolean verifyIfIsAVideo(MultipartFile file) {
        return isVideo(file);
    }

    private static boolean isImage(MultipartFile file) {
        if (file == null) {
            throw new FileNullContentException();
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            throw new FileErrorException("File name is null!");
        }

        boolean isImage;
        isImage = fileName.toLowerCase().endsWith(".jpg");
        isImage = fileName.toLowerCase().endsWith(".jpeg");
        isImage = fileName.toLowerCase().endsWith(".png");

        return isImage;
    }



    private static Boolean isPDF(MultipartFile file) throws FileNullContentException {
        if (file == null) {
            throw new FileNullContentException();
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            throw new FileErrorException("File name is null!");
        }

        return fileName.toLowerCase().endsWith(".pdf");
    }



    private static Boolean isVideo(MultipartFile file) {
        if (file == null) {
            throw new FileNullContentException();
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            throw new FileErrorException("File name is null!");
        }

        return fileName.toLowerCase().endsWith(".mp4");
    }
}
