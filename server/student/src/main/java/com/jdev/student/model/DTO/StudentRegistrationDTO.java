package com.jdev.student.model.DTO;

import com.jdev.student.model.enums.EtinyEnum;
import com.jdev.student.model.enums.SemesterEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public record StudentRegistrationDTO(
        String completeName,
        String email,
        String password,
        String cpf,
        Date birthday,
        String city,
        String nationatily,
        EtinyEnum ethnicity,
        String phone,
        String address,
        String numberHouse
) {
}
