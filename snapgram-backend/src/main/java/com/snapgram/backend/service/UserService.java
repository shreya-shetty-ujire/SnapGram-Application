package com.snapgram.backend.service;



import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.exception.AuthenticationException;
import com.snapgram.backend.exception.UserNotFoundException;
import com.snapgram.backend.model.User;

import java.util.List;


public interface UserService {
    User saveUser(UserRequestDto userRequestDto);
    User findUserByUsername(String username);
    List<User> findUsersByIds(List<Integer> userIds);
    void deleteUser(String token);
    User findUserProfile(String token)  throws UserNotFoundException;
    String followUser(Integer reqUserId, Integer followUserId );
    String unFollowUser(Integer reqUserId, Integer followUserId );
    User findUserById(Integer userId);
    // Search users takes email, usernames or full names
    List<User> searchUsers(String query);

    User updateUser(User updatedUser, User existingUser);
    boolean passwordMatches(String password, String encodedPassword);
}
