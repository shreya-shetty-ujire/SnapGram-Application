package com.snapgram.backend.model;


import com.snapgram.backend.DTO.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Integer postId;

    private String image;

    @Size(max = 255, message = "Caption cannot exceed 255 characters")
    private String caption;

    private LocalDateTime createdAt;

    private String location;

    // Prevent circular reference between User and Post
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "userId", column = @Column(name = "user_id")),
            @AttributeOverride(name = "email", column = @Column(name = "user_email")),
            @AttributeOverride(name = "username", column = @Column(name = "user_username")),
            @AttributeOverride(name = "userImage", column = @Column(name = "user_image")),
            @AttributeOverride(name = "location", column = @Column(name = "location"))
    })
    private UserDto user;

    @ElementCollection
    @CollectionTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "post_id")
    )
    @AttributeOverrides({
            @AttributeOverride(name = "userId", column = @Column(name = "like_user_id")),
            @AttributeOverride(name = "username", column = @Column(name = "like_user_username"))
    })
    private Set<UserDto> likes = new HashSet<>();


    @OneToMany
    private List<Comments> comments = new ArrayList<Comments>();

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Post(Integer postId, String image, String caption, LocalDateTime createdAt, UserDto user,
                Set <UserDto> likes, List <Comments> comments, String location) {
        this.postId = postId;
        this.image = image;
        this.caption = caption;
        this.createdAt = createdAt;
        this.user = user;
        this.likes = likes;
        this.comments = comments;
        this.location=location;
    }

    public Post() {
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer post_id) {
        this.postId = post_id;
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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Set <UserDto> getLikes() {
        return likes;
    }

    public void setLikes(Set <UserDto> likes) {
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
