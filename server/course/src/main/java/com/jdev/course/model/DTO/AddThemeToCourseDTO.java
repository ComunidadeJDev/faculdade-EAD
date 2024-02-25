package com.jdev.course.model.DTO;

import com.jdev.course.model.enums.ThemesEnum;

import java.util.List;

public record AddThemeToCourseDTO(
        List<ThemesEnum> themes,
        String registerDiscipline
) {
}
