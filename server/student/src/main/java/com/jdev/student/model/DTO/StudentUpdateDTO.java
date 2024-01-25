package com.jdev.student.model.DTO;

import com.jdev.student.model.enums.EtinyEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public record StudentUpdateDTO(
        String completeName,
        String registration,
        String email,
        String cpf,
        Date birthday,
        String city,
        String nationatily,
        EtinyEnum ethnicity,
        String phone,
        MultipartFile BulletinEnemFile,
        MultipartFile cpfFile,
        MultipartFile rgFile,
        MultipartFile certificateOfCompletionFile,
        String address,
        String numberHouse
) {
}
