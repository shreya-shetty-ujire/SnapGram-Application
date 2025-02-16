package com.snapgram.backend.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//For API Request - Receiving Data
public class UserRequestDto {
    private String username;
    private String email;
    private String name;

    @NotNull(message = "Password cannot be null")
    @Size(min=8,message="Password must be at least 8 characters long")
    @Pattern(regexp="^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d!@#$%^&*]+$",message="Password must have atleast 1 Uppercase, 1 digit")
    private String password;

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

    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
