package com.jdev.authentication.config;

import com.jdev.authentication.domain.UserEntity;
import com.jdev.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FirstUserConfig implements ApplicationRunner {

    private final UserRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userEntityRepository.count() != 0) {
            return;
        }

        log.info("Nenhum usuário encontrado, cadstrando usuários padrão.");

        userEntityRepository.save(
                UserEntity.builder()
                        .name("Administrador")
                        .email("admin@email.com")
                        .password(passwordEncoder.encode("123456"))
                        .role("ADMIN")
                        .build()
        );
    }
}
