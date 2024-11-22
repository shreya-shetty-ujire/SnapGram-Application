package com.snapgram.backend.DTO;

import jakarta.validation.constraints.NotBlank;

public class FollowDto {
    private long id;
    @NotBlank(message = "Username cannot be blank")
    private String userName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
