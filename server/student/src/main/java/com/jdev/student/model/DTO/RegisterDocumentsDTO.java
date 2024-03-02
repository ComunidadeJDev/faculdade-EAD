package com.jdev.student.model.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record RegisterDocumentsDTO(
        String username,
        MultipartFile cpf,
        MultipartFile rg,
        MultipartFile certificateOfCompletion
) {
}
