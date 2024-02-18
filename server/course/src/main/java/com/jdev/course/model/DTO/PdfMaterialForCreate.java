package com.jdev.course.model.DTO;

import org.springframework.web.multipart.MultipartFile;

public record PdfMaterialForCreate(
        MultipartFile file,
        String username
) {
}
