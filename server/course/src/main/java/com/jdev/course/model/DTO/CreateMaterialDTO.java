package com.jdev.course.model.DTO;

import com.jdev.course.model.enums.MaterialTypeEnum;
import org.springframework.web.multipart.MultipartFile;

public record CreateMaterialDTO (
        String name,
        MultipartFile file,
        String registrationDiscipline
) {
}
