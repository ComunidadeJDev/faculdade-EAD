package com.jdev.authentication.model.DTO;

import com.jdev.authentication.model.User;

public record LoginResponseDTO(
        User user,
        String jwt
) {
}
