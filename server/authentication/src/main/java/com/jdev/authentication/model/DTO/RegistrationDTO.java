package com.jdev.authentication.model.DTO;

import com.jdev.authentication.model.Role;

public record RegistrationDTO(
        String username,
        String password,
        String role
) {
}
