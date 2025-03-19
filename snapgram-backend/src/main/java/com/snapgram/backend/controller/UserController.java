package com.snapgram.backend.controller;



import com.snapgram.backend.config.JwtUtil;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/id/{id}")
    public ResponseEntity<User> findUserById(@PathVariable @Valid Integer id){
        User user=userService.findUserById(id);
        return new ResponseEntity <>(user,HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<User> updateUser(
                                             @RequestBody  User updatedUser,
                                             @RequestHeader("Authorization") String token){
        User user=userService.findUserProfile(token);
        User editedUser= userService.updateUser(updatedUser,user);
        return new ResponseEntity <>(editedUser, HttpStatus.OK);
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<User> findUserByUsernameHandler(@PathVariable String username){
        User user=userService.findUserByUsername(username);
        return new ResponseEntity <>(user,HttpStatus.OK);
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
    public ResponseEntity<User> findUserProfileHandler(@RequestHeader("Authorization") String token){

        User user=userService.findUserProfile(token);
        return new ResponseEntity <>(user, HttpStatus.OK);
    }

    @GetMapping("/findByIds/{userIds}")
    public ResponseEntity<List<User>> findUserByUserIdsHandler(@PathVariable List<Integer> userIds){
        List<User> users=userService.findUsersByIds(userIds);
        return new ResponseEntity <>(users,HttpStatus.OK);
    }

    @GetMapping("/search")  // http://localhost/3030/search?id="searchedId"
    public ResponseEntity<List<User>> searchUsersHandler(@RequestParam("id") String query){
        List<User> users= userService.searchUsers(query);
        return new ResponseEntity <>(users,HttpStatus.OK);
    }




    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestHeader("Authorization") String token){
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        userService.deleteUser(jwtToken);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/protected")
    public ResponseEntity<String> getProtectedData() {
        return ResponseEntity.ok("You have accessed a protected resource!");
    }
}
