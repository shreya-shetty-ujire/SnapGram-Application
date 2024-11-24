package com.snapgram.backend.service;


import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User saveUser(UserRequestDto userRequestDto);
    List <User> getAllUsers();
    Optional <User> getUserById(long userId);
    void updateUser(UserRequestDto userRequestDto);
    Optional<User> deleteUser(User user);
}
