package com.snapgram.backend.DTO;

import java.util.List;

import jakarta.validation.constraints.*;

public class UserDto {
    public class UserDTO {
        private long userId;

        @NotBlank(message = "Username cannot be blank")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        private String userName;

        @NotBlank(message = "First name cannot be blank")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "First name must only contain letters and spaces")
        private String firstName;

        @Pattern(regexp = "^[a-zA-Z ]+$", message = "Last name must only contain letters")
        private String lastName;

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        private String email;

//        List of posts created by the user
        private List <PostPreviewDto> posts;

        // List of likes made by the user
        private List <LikesDto> likes;

//        List of followers
        private List <FollowDto> followers;

//        List of people the user is following
        private List <PostPreviewDto> following;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List <PostPreviewDto> getPosts() {
            return posts;
        }

        public void setPosts(List <PostPreviewDto> posts) {
            this.posts = posts;
        }

        public List <FollowDto> getFollowers() {
            return followers;
        }

        public void setFollowers(List <FollowDto> followers) {
            this.followers = followers;
        }

        public List <LikesDto> getLikes() {
            return likes;
        }

        public void setLikes(List <LikesDto> likes) {
            this.likes = likes;
        }

        public List <PostPreviewDto> getFollowing() {
            return following;
        }

        public void setFollowing(List <PostPreviewDto> following) {
            this.following = following;
        }
    }

}
