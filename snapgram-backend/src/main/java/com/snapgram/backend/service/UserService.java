package com.snapgram.backend.service;


import com.snapgram.backend.DTO.UserDto;
import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.DTO.UserUpdateDto;
import com.snapgram.backend.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    void saveUser(UserRequestDto userRequestDto);
    List <UserDto> getAllUsers();
    User getUserByUsername(String username);
    void updateUser(Long userId, UserUpdateDto userUpdateDto);
    void deleteUser(Long userId);
}
