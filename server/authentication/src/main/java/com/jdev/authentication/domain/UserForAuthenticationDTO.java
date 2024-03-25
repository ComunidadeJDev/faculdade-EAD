package com.jdev.authentication.domain;

public record UserForAuthenticationDTO(
        String name,
        String email,
        String password,
        String type
) {
}
