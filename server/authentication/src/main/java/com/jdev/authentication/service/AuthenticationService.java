package com.jdev.authentication.service;

import com.jdev.authentication.model.DTO.LoginResponseDTO;
import com.jdev.authentication.model.Role;
import com.jdev.authentication.model.User;
import com.jdev.authentication.repository.RoleRepository;
import com.jdev.authentication.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    private final JwtDecoder jwtDecoder;

    @Autowired
    public AuthenticationService(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    public User registerUser(String username, String password, String role) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority(role).get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        return userRepository.save(new User(null, username, encodedPassword, authorities));
    }

    public LoginResponseDTO loginUser(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
        } catch (Exception ex) {
            return new LoginResponseDTO(null, ex.getMessage());
        }
    }

    public boolean validateToken(String token) {
        try {
            token = token.startsWith("Bearer ") ? token.substring(7) : token;
            Jwt jwt = jwtDecoder.decode(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
