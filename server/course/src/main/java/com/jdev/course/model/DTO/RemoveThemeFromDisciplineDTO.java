package com.jdev.course.model.DTO;

import com.jdev.course.model.enums.ThemesEnum;

public record RemoveThemeFromDisciplineDTO(
        String registerDiscipline,
        ThemesEnum theme
) {
}
