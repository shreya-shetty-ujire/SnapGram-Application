package com.snapgram.backend.DTO;

import com.snapgram.backend.model.User;
import com.snapgram.backend.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponseDto {
    private Integer userId;
    private String username;
    private String email;
    private String phoneNumber;
    private List <Integer> savedPostIds;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.savedPostIds = user.getSavedPosts().stream()
            .map(Post::getPostId)
            .collect(Collectors.toList());
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List <Integer> getSavedPostIds() {
        return savedPostIds;
    }

    public void setSavedPostIds(List <Integer> savedPostIds) {
        this.savedPostIds = savedPostIds;
    }
}
