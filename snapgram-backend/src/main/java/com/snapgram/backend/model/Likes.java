package com.snapgram.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="like_id")
    private long likeId;

    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id",nullable=false)
    private Post post;

    private LocalDateTime createdAt;

    public long getLikeId() {
        return likeId;
    }

    public void setLikeId(long likeId) {
        this.likeId = likeId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
