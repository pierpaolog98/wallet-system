package com.example.userService.controller;

import com.example.userService.dto.UserDTO;
import com.example.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<Long> createUser(@RequestBody @Valid UserDTO userDTO){
        Long id = userService.createUser(userDTO);
        return ResponseEntity.ok(id);
    }


}
