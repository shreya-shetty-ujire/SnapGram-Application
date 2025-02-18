package com.snapgram.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="comment_id")
    private Integer commentId;

    @Column(name="comment_text")
    @Size(max = 500, message = "Comments preview cannot exceed 500 characters")
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    private User user;


    @ManyToMany
    private Set <User> likes=new HashSet <>();

    @Column(name="created_At")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="post_id", nullable = false)
    private Post post;

    public Comments(Integer commentId, User user, String content, LocalDateTime createdAt, Set <User> likes) {
        this.commentId = commentId;
        this.user = user;
        this.content = content;
        this.createdAt = createdAt;
        this.likes = likes;
    }

    public Comments() {
    }

    public Set <User> getLikes() {
        return likes;
    }

    public void setLikes(Set <User> likes) {
        this.likes = likes;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public @Size(max = 500, message = "Comments preview cannot exceed 500 characters") String getContent() {
        return content;
    }

    public void setContent(@Size(max = 500, message = "Comments preview cannot exceed 500 characters") String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
