package com.snapgram.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private long userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String email;

    private String password;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy="user",cascade =CascadeType.ALL)
    private List <Posts> posts;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Likes> likes;

    @OneToMany(mappedBy = "followes", cascade=CascadeType.ALL)
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
}
