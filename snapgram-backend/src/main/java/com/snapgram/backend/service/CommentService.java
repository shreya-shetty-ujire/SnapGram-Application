package com.snapgram.backend.service;

import com.snapgram.backend.exception.CommentException;
import com.snapgram.backend.exception.PostException;
import com.snapgram.backend.exception.UserException;
import com.snapgram.backend.model.Comments;

import java.util.List;

public interface CommentService {

    Comments createComment(Comments comment, Integer postId, Integer userId) throws UserException, PostException;
    List <Comments> findCommentsByPostId(Integer postId) throws CommentException, PostException;
    Comments findCommentById(Integer commentId) throws CommentException;
    Comments likeComment(Integer commentId, Integer userId) throws CommentException, UserException;
    Comments unlikeComment(Integer commentId, Integer userId) throws CommentException, UserException;
    String deleteComment(Integer commentId, Integer postId, Integer userId ) throws CommentException, UserException;
}
