package com.snapgram.backend.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="post")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "postId")
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="post_id")
    private Integer postId;

    private String image;

    @Size(max = 255, message = "Caption cannot exceed 255 characters")
    private String caption;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> likes = new HashSet<>();

    @OneToMany(mappedBy = "post",cascade= CascadeType.ALL)
    private List<Comments> comments = new ArrayList <Comments>();


    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer post_id) {
        this.postId = post_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Set <User> getLikes() {
        return likes;
    }

    public void setLikes(Set <User> likes) {
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
