package com.snapgram.backend.utilities;

import com.snapgram.backend.DTO.PostDto;
import com.snapgram.backend.DTO.UserDto;
import com.snapgram.backend.model.Post;

public class PostDtoMapper {
    public static PostDto convertToPostDto(Post post) {
        UserDto userDto = UserDtoMapper.convertToUserDto(post.getUser()); // Using UserMapper to convert User to UserDto
        return new PostDto(
                post.getPostId(),
                post.getImage(),
                post.getCaption(),
                post.getCreatedAt(),
                userDto
        );
    }
}
