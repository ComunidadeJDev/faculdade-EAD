package com.jdev.authentication.configuration;

import com.jdev.authentication.model.Role;
import com.jdev.authentication.model.User;
import com.jdev.authentication.repository.RoleRepository;
import com.jdev.authentication.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class CreateRoles {

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
            if (roleRepository.findByAuthority("USER").isPresent()) return;
            if (roleRepository.findByAuthority("TEACHER").isPresent()) return;
            if (roleRepository.findByAuthority("COORDINATOR").isPresent()) return;
            if (roleRepository.findByAuthority("DIRECTOR").isPresent()) return;
            if (roleRepository.findByAuthority("FINANCIAL").isPresent()) return;

            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("TEACHER"));
            roleRepository.save(new Role("COORDINATOR"));
            roleRepository.save(new Role("DIRECTOR"));
            roleRepository.save(new Role("FINANCIAL"));
            Role adminRole = roleRepository.save(new Role("ADMIN"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User(1, "admin", passwordEncoder.encode("password"), roles);
            userRepository.save(admin);
        };
    }


}
