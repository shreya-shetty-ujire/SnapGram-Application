package com.snapgram.backend.model;

import com.snapgram.backend.DTO.UserDto;
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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="user_id")),
            @AttributeOverride(name="email", column=@Column(name="user_email")),
    })
    private UserDto user;


    @Embedded
    @ElementCollection
    private Set <UserDto> likes=new HashSet <>();

    @Column(name="created_At")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="post_id", nullable = false)
    private Post post;

    public Comments(Integer commentId, String content, UserDto user, Set <UserDto> likes, LocalDateTime createdAt, Post post) {
        super();
        this.commentId = commentId;
        this.content = content;
        this.user = user;
        this.likes = likes;
        this.createdAt = createdAt;
        this.post = post;
    }

    public Comments() {
    }



    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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
