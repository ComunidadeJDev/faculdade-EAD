package com.jdev.authentication.infra.queues;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdev.authentication.domain.CreateUserEntity;
import com.jdev.authentication.domain.UserEntity;
import com.jdev.authentication.repository.UserRepository;
import com.jdev.authentication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
public class UserForAuthenticationSubscriber {

    private UserService userService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserForAuthenticationSubscriber(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RabbitListener(queues = "${mq.queue.user-for-authentication}")
    public void responseOfTheRequestUserForAuthentication(@Payload String payload) {
        CreateUserEntity user = this.convertJsonInCourse(payload);
        UserEntity userEntity = modelingUserForSaveInDatabase(user);

        this.userRepository.save(userEntity);
    }

    private UserEntity modelingUserForSaveInDatabase(CreateUserEntity user) {
        return UserEntity.builder()
                .name(user.name())
                .email(user.email())
                .password(passwordEncoder.encode(user.password()))
                .role(user.role().toString())
                .createdAt(LocalDateTime.now())
                .build();
    }

    private CreateUserEntity convertJsonInCourse(String payload) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(payload, CreateUserEntity.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
