package com.snapgram.backend.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.snapgram.backend.model.Post;
import com.snapgram.backend.model.User;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;

@Embeddable
public class UserDto {


    public UserDto(Integer userId, String username, String name, String email, String userImage, Set<User> following,
                   Set<User> followers, String bio, String phoneNumber, String gender, List<Post> savedPosts) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.email = email;
        this.bio=bio;
        this.phoneNumber=phoneNumber;
        this.gender=gender;
        this.userImage = userImage;
        this.following=following;
        this.followers=followers;
        this.savedPosts=savedPosts.stream().map(Post::getPostId).toList();
    }

        private Integer userId;

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

    private Set<User> followers;
    private Set <User> following;

    private List<Integer> savedPosts=new ArrayList <>();
    private String gender;
    private String bio;
    private String phoneNumber;

    public Set <User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set <User> followers) {
        this.followers = followers;
    }

    public Set <User> getFollowing() {
        return following;
    }

    public void setFollowing(Set <User> following) {
        this.following = following;
    }

    public UserDto() {

    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
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

    public List <Integer> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(List <Integer> savedPosts) {
        this.savedPosts = savedPosts;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
