package com.snapgram.backend.controller;


import com.snapgram.backend.DTO.UserDto;
import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.DTO.UserUpdateDto;
import com.snapgram.backend.model.User;
import com.snapgram.backend.response.MessageResponse;
import com.snapgram.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findUserById(@PathVariable @Valid Integer id){
        User user=userService.findUserById(id);
        return new ResponseEntity <User>(user,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(
                                             @RequestBody @Valid UserUpdateDto userUpdateDto){
//        userService.updateUser(id,userUpdateDto);
//        return new ResponseEntity <>("User updated successfully",HttpStatus.OK);
        return null;
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<User> findUserByUsernameHandler(@PathVariable String username){
        User user=userService.findUserByUsername(username);
        return new ResponseEntity <>(user,HttpStatus.OK);
    }

    @PutMapping("follow/{followUserId}")
    public ResponseEntity<MessageResponse> followUserHandler(@PathVariable Integer followUserId){
//        List <UserDto> userDtoList=userService.followUser(Integer);

        return null;
    }

    @PutMapping("unfollow/{unfollowUserId}")
    public ResponseEntity<MessageResponse> unfollowUserHandler(@PathVariable Integer unfollowUserId){
//        List <UserDto> userDtoList=userService.followUser(Integer);

        return null;
    }
    @GetMapping("/reqProfile")
    public ResponseEntity<List<UserDto>> findUserProfileHandler(){
        return null;
    }

    @GetMapping("/findByIds")
    public ResponseEntity<List<User>> findUserByUserIdsHandler(@PathVariable List<Integer> userIds){
        List<User> users=userService.findUsersByIds(userIds);
        return new ResponseEntity <>(users,HttpStatus.OK);
    }

    @GetMapping("/search")  // http://localhost/3030/search?id="searchedId"
    public ResponseEntity<List<User>> searchUsersHandler(@RequestParam("id") String query){
        List<User> users= userService.searchUsers(query);
        return new ResponseEntity <>(users,HttpStatus.OK);
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
