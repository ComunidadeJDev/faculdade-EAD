package com.jdev.authentication.api;

import com.jdev.authentication.domain.CreateUserEntity;
import com.jdev.authentication.domain.UserEntity;
import com.jdev.authentication.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authentication")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/create")
//    public ResponseEntity<Object> createUser(@RequestBody @Valid CreateUserEntity request) {
//        return ResponseEntity.ok().body(userService.createUserEntity(request));
//    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }
}
