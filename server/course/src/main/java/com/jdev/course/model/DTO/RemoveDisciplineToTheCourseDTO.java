package com.jdev.course.model.DTO;

import com.jdev.course.model.enums.SemesterEnum;

public record RemoveDisciplineToTheCourseDTO(
        String registerDiscipline,
        String registerCourse,
        SemesterEnum semester
) {
}
