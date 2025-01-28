package com.snapgram.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.constraints.*;

@Entity
@Table(name="app_user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private long userId;

    @Column(name="user_name", unique=true)
    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String userName;

    @NotNull(message = "First name cannot be null")
    @Pattern(regexp="^[a-zA-Z ]+$", message="First name must must not contain special characters")
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    private String password;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy="user",cascade =CascadeType.ALL)
    private List <Comments> comments;

    @OneToMany(mappedBy="user",cascade =CascadeType.ALL)
    private List <Posts> posts;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Likes> likes;

    @OneToMany(mappedBy = "follower", cascade=CascadeType.ALL)
    private List<Follows> followers;

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    private List<Follows> following;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getUserName() {
        return userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(long userId) {
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

    public List <Posts> getPosts() {
        return posts;
    }

    public void setPosts(List <Posts> posts) {
        this.posts = posts;
    }

    public List <Likes> getLikes() {
        return likes;
    }

    public void setLikes(List <Likes> likes) {
        this.likes = likes;
    }

    public List <Follows> getFollowers() {
        return followers;
    }

    public void setFollowers(List <Follows> followers) {
        this.followers = followers;
    }

    public List <Follows> getFollowing() {
        return following;
    }

    public void setFollowing(List <Follows> following) {
        this.following = following;
    }

    public void setComments(List <Comments> comments) {
        this.comments = comments;
    }

    public List <Comments> getComments() {
        return comments;
    }

}
