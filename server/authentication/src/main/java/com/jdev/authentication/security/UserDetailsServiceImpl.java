package com.jdev.authentication.security;

import com.jdev.authentication.domain.UserEntity;
import com.jdev.authentication.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("UserNotFound!"));
        final var simpleGratedAuthority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
        return new User(
                user.getEmail(),
                user.getPassword(),
                List.of(simpleGratedAuthority));
    }
}
