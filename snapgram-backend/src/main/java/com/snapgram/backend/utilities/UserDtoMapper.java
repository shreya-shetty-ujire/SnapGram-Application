package com.snapgram.backend.utilities;

import com.snapgram.backend.DTO.UserDto;
import com.snapgram.backend.model.User;

public class UserDtoMapper {
    public static UserDto convertToUserDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getUserImage(),
                user.getFollowing(),
                user.getFollowers(),
                user.getBio(),
                user.getPhoneNumber(),
                user.getGender(),
                user.getSavedPosts()
        );
    }
}
