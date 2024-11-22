package com.snapgram.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Follows {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="following_user_id",nullable=false)
    private User following;

    @ManyToOne
    @JoinColumn(name="follower_user_id",nullable=false)
    private User follower;

    private LocalDateTime createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public User getFollowed() {
        return follower;
    }

    public void setFollowed(User followed) {
        this.follower = followed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
