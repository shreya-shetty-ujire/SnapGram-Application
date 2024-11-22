package com.snapgram.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="comment_id")
    private long commentId;

    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id",nullable=false)
    private Posts post;

    @Column(name="comment_text")
    @Size(max = 500, message = "Comments preview cannot exceed 500 characters")
    private String commentText;

    @Column(name="created_At")
    private LocalDateTime createdAt;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
