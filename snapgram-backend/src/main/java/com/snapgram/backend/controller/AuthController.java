package com.snapgram.backend.controller;

import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.config.JwtUtil;
import com.snapgram.backend.model.User;
import com.snapgram.backend.service.AuthService;
import com.snapgram.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequestDto userRequestDto) {
        String jwtToken=authService.authenticateUser(userRequestDto);
        return new ResponseEntity <>(jwtToken, HttpStatus.OK);
    }
}
