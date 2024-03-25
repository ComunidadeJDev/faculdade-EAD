package com.jdev.authentication.service;

import com.jdev.authentication.domain.CreateUserEntity;
import com.jdev.authentication.domain.UserEntity;
import com.jdev.authentication.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUserEntity(UserEntity user) {
        userRepository.save(user);
    }

//    public UserEntity modelingUserEntity(CreateUserEntity userData) {
//        return UserEntity.builder()
//                .name(userData.name())
//                .email(userData.email())
//                .password(userData.password())
//                .role(userData.role())
//                .createdAt(LocalDateTime.now())
//                .build();
//    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

}