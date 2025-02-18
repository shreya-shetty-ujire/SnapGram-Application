package com.snapgram.backend.controller;


import com.snapgram.backend.DTO.UserDto;
import com.snapgram.backend.model.User;
import com.snapgram.backend.response.MessageResponse;
import com.snapgram.backend.service.UserService;
import com.snapgram.backend.utilities.UserDtoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable @Valid Integer id){
        User user=userService.findUserById(id);
        return new ResponseEntity <>(UserDtoMapper.convertToUserDto(user),HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<UserDto> updateUser(
                                             @RequestBody @Valid UserDto userDto,
                                             @RequestHeader("Authorization") String token){
        User user=userService.findUserProfile(token);
        User updatedUser= userService.updateUser(user.getUserId(),userDto);
        return new ResponseEntity <>(UserDtoMapper.convertToUserDto(updatedUser), HttpStatus.OK);
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<UserDto> findUserByUsernameHandler(@PathVariable String username){
        User user=userService.findUserByUsername(username);
        return new ResponseEntity <>(UserDtoMapper.convertToUserDto(user),HttpStatus.OK);
    }

    @PutMapping("follow/{followUserId}")
    public ResponseEntity<MessageResponse> followUserHandler(@PathVariable Integer followUserId, @RequestHeader("Authorization") String token){
        User user=userService.findUserProfile(token);

        String message=userService.followUser(user.getUserId(), followUserId);
        MessageResponse result=new MessageResponse(message);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("unfollow/{unfollowUserId}")
    public ResponseEntity<MessageResponse> unfollowUserHandler(@PathVariable Integer unfollowUserId, @RequestHeader("Authorization") String token){
        User user=userService.findUserProfile(token);

        String message=userService.unFollowUser(user.getUserId(), unfollowUserId);
        MessageResponse result=new MessageResponse(message);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/reqProfile")
    public ResponseEntity<UserDto> findUserProfileHandler(@RequestHeader("Authorization") String token){
        User user=userService.findUserProfile(token);
        UserDto userDto=UserDtoMapper.convertToUserDto(user);
        return new ResponseEntity <>(userDto, HttpStatus.OK);
    }

    @GetMapping("/findByIds")
    public ResponseEntity<List<UserDto>> findUserByUserIdsHandler(@PathVariable List<Integer> userIds){
        List<User> users=userService.findUsersByIds(userIds);
        List<UserDto> userDtos=users.stream().map(UserDtoMapper::convertToUserDto).toList();
        return new ResponseEntity <>(userDtos,HttpStatus.OK);
    }

    @GetMapping("/search")  // http://localhost/3030/search?id="searchedId"
    public ResponseEntity<List<UserDto>> searchUsersHandler(@RequestParam("id") String query){
        List<User> users= userService.searchUsers(query);
        List<UserDto> userDto=users.stream().map(UserDtoMapper::convertToUserDto).toList();
        return new ResponseEntity <>(userDto,HttpStatus.OK);
    }




    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity <>("User deleted successfully ",HttpStatus.OK);
    }

    @GetMapping("/protected")
    public ResponseEntity<String> getProtectedData() {
        return ResponseEntity.ok("You have accessed a protected resource!");
    }
}
