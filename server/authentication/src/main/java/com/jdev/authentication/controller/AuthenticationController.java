package com.jdev.authentication.controller;

import com.jdev.authentication.model.DTO.LoginResponseDTO;
import com.jdev.authentication.model.DTO.RegistrationDTO;
import com.jdev.authentication.model.User;
import com.jdev.authentication.service.AuthenticationService;
import com.jdev.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegistrationDTO body) {
        User user = authenticationService.registerUser(body.username(), body.password());
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody RegistrationDTO body) {
        return ResponseEntity.ok().body(authenticationService.loginUser(body.username(), body.password()));
    }

    @GetMapping("/user/list")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/test")
    public ResponseEntity<Object> test() {
        return ResponseEntity.ok().body("vc esta como usuário");
    }
}
