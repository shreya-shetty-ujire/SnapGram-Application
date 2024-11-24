package com.snapgram.backend.controller;


import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRequestDto userRequestDto){
        userService.saveUser(userRequestDto);
        return new ResponseEntity<>("User created successfully!", HttpStatus.CREATED); //201
    }
}
