package com.snapgram.backend.controller;

import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.model.User;
import com.snapgram.backend.service.AuthService;
import com.snapgram.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRequestDto userRequestDto){
        User user= userService.saveUser(userRequestDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED); //201
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserRequestDto userRequestDto) {
        String jwtToken=authService.authenticateUser(userRequestDto.getUsername(),userRequestDto.getPassword());
        User user = userService.findUserByUsername(userRequestDto.getUsername());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);


        // Return the response body with the JWT token
        return new ResponseEntity<>(user, headers , HttpStatus.OK);
    }
}
