package com.snapgram.backend.utilities;

import com.snapgram.backend.DTO.UserDto;
import com.snapgram.backend.model.User;

public class UserDtoMapper {
    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto(user.getUserId(),user.getUsername(), user.getName(),user.getEmail(),
                user.getUserImage());
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        return userDto;
    }
}
