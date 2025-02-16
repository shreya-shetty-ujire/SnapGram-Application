package com.snapgram.backend.DTO;

import java.util.List;

import jakarta.validation.constraints.*;

public class UserDto {

    public UserDto(long userId, String username, String name, String email, String userImage) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.email = email;
        this.userImage = userImage;
    }

        private long userId;

        @NotBlank(message = "Username cannot be blank")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        private String username;




    @NotBlank(message = "First name cannot be blank")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "First name must only contain letters and spaces")
        private String name;


        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        private String email;

    private String userImage;

    public UserDto() {

    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String userName) {
            this.username = userName;
        }

        public String getName() {
            return name;
        }

        public void setName(String firstName) {
            this.name = firstName;
        }



        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }


}
