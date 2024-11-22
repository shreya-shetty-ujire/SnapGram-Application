package com.snapgram.backend.DTO;

import com.snapgram.backend.model.Posts;
import com.snapgram.backend.model.User;
import jakarta.validation.constraints.*;

public class CommentsDto {
    private long commentId;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String userName;
    private long postId;

    @Size(max = 500, message = "Comment text cannot exceed 500 characters")
    private String commentText;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
