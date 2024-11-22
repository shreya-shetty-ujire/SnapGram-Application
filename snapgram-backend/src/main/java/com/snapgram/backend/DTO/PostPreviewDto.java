package com.snapgram.backend.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public class PostPreviewDto {
    private long postId;
    @Size(max = 255, message = "Caption cannot exceed 255 characters")
    private String caption;

    @PositiveOrZero(message = "Likes count cannot be negative")
    private int likes;

    @Valid
    private List <CommentsDto> comments;
    // displaying the username of the poster alongside the post's content
    @NotBlank(message = "Username cannot be blank")
    private String username;


    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List <CommentsDto> getComments() {
        return comments;
    }

    public void setComments(List <CommentsDto> comments) {
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

