package com.snapgram.backend.model;


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

    @Column(name = "user_name", unique = true)
    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotNull(message = "First name cannot be null")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "First name must must not contain special characters")
    @Column(name = "first_name")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    private String phoneNumber;

    private String gender;

    private String userImage;

    @NotNull
    private String password;

    private String bio;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "user_followers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set <User> followers = new HashSet <>();

    @ManyToMany
    @JoinTable(
            name = "user_following",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private Set <User> following = new HashSet <>();

    @ManyToMany //Many posts can be saved by multiple users
    private List <Post> savedPosts = new ArrayList <>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List <Comments> comments;


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

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }


    public String getUsername() {
        return username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUsername(String userName) {
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

    public List <Post> getPosts() {
        return savedPosts;
    }

    public void setPosts(List <Post> posts) {
        this.savedPosts = posts;
    }

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

    public void setComments(List <Comments> comments) {
        this.comments = comments;
    }

    public List <Comments> getComments() {
        return comments;
    }

}
