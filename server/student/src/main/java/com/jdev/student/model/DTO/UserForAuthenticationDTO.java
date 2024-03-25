package com.jdev.student.model.DTO;

public record UserForAuthenticationDTO(
        String name,
        String email,
        String password,
        String role
) {
}
