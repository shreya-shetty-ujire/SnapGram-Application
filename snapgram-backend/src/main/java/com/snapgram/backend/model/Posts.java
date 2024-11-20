package com.snapgram.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="post_id")
    private long postId;

    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    private User user;
    private String caption;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post",cascade= CascadeType.ALL)
    private List <Likes> likes;

    @OneToMany(mappedBy = "post",cascade= CascadeType.ALL)
    private List<Comments> comments;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long post_id) {
        this.postId = post_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List <Likes> getLikes() {
        return likes;
    }

    public void setLikes(List <Likes> likes) {
        this.likes = likes;
    }

    public List <Comments> getComments() {
        return comments;
    }

    public void setComments(List <Comments> comments) {
        this.comments = comments;
    }

    // Relationship


}
