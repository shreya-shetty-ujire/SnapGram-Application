package com.snapgram.backend.controller;


import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.DTO.UserUpdateDto;
import com.snapgram.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestBody @Valid UserUpdateDto userUpdateDto){
        userService.updateUser(id,userUpdateDto);
        return new ResponseEntity <>("User updated successfully",HttpStatus.OK);
    }
}
