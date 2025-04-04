package com.snapgram.backend.model;



import com.snapgram.backend.DTO.UserDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.*;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotNull(message = "Name cannot be null")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "First name must must not contain special characters")
    private String name;

    @NotNull
    private String password;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    private String phoneNumber;
    private String gender;
    private String userImage;
    private String bio;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Prevents circular references for followers/following
    @Embedded
    @ElementCollection
    private Set<UserDto> followers = new HashSet<>();

    @Embedded
    @ElementCollection
    private Set<UserDto> following = new HashSet<>();

    // Prevents circular references for saved posts

    @ManyToMany
    private List<Post> savedPosts = new ArrayList<>();


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(@NotNull String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public List <Post> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(List <Post> savedPosts) {
        this.savedPosts = savedPosts;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUsername() {
        return username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set <UserDto> getFollowers() {
        return followers;
    }

    public User(Integer userId, String username, String name, String password, String email, String phoneNumber, String gender, String userImage, String bio, LocalDateTime createdAt, Set <UserDto> followers, Set <UserDto> following, List <Post> savedPosts) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.userImage = userImage;
        this.bio = bio;
        this.createdAt = createdAt;
        this.followers = followers;
        this.following = following;
        this.savedPosts = savedPosts;
    }

    public void setFollowers(Set <UserDto> followers) {
        this.followers = followers;
    }

    public Set <UserDto> getFollowing() {
        return following;
    }

    public void setFollowing(Set <UserDto> following) {
        this.following = following;
    }

    public User() {
    }
}
