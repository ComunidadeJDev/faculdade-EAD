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

import javax.management.relation.RoleNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserForAuthenticationSubscriber {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserForAuthenticationSubscriber(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RabbitListener(queues = "${mq.queue.user-for-authentication}")
    public void responseOfTheRequestUserForAuthentication(@Payload String payload) throws RoleNotFoundException {
        CreateUserEntity user = this.convertJsonInCourse(payload);
        UserEntity userEntity = modelingUserForSaveInDatabase(user);

        this.userRepository.save(userEntity);
    }

    private UserEntity modelingUserForSaveInDatabase(CreateUserEntity user) throws RoleNotFoundException {
        List<String> aceptedRoles = List.of("ADMIN", "STUDENT", "COORDINATOR", "TEACHER", "DIRECTOR", "FINANCIAL");
            if (aceptedRoles.contains(user.role())) {
                return UserEntity.builder()
                        .name(user.name())
                        .email(user.email())
                        .password(passwordEncoder.encode(user.password()))
                        .role(user.role())
                        .createdAt(LocalDateTime.now())
                        .build();
            } else {
                throw new RoleNotFoundException();
            }
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
