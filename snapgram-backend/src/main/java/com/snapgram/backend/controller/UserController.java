package com.snapgram.backend.controller;


import com.snapgram.backend.DTO.UserDto;
import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.DTO.UserUpdateDto;
import com.snapgram.backend.model.User;
import com.snapgram.backend.service.UserService;
import com.snapgram.backend.utilities.UserDtoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username){
        User user=userService.getUserByUsername(username);
        UserDto userDto= UserDtoMapper.toUserDto(user);
        return new ResponseEntity <>(userDto,HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List <UserDto> userDtoList=userService.getAllUsers();

        return new ResponseEntity <>(userDtoList,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        String username=userService.deleteUser(userId);
        return new ResponseEntity <>("User deleted successfully ",HttpStatus.OK);
    }
}
