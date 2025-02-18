package com.snapgram.backend.DTO;



import java.time.LocalDateTime;

public class PostDto {

    private Integer postId;
    private String image;
    private String caption;
    private LocalDateTime createdAt;
    private UserDto userDto;

    public PostDto() {}

    public PostDto(Integer postId, String image, String caption, LocalDateTime createdAt, UserDto userDto) {
        this.postId = postId;
        this.image = image;
        this.caption = caption;
        this.createdAt = createdAt;
        this.userDto=userDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
