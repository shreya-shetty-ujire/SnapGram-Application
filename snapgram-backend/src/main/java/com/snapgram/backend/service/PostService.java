package com.snapgram.backend.service;

import com.snapgram.backend.exception.PostException;
import com.snapgram.backend.exception.UserException;
import com.snapgram.backend.model.Post;

import java.util.List;



public interface PostService {

    Post createPost(Post post, Integer userId) throws UserException;

    String deletePost(Integer postId, Integer userId) throws UserException, PostException;

    List <Post> getPostsByUserId(Integer userId) throws UserException;

    Post getPostById(Integer postId) throws PostException;

    // Get Posts by userIds
    List<Post> getAllPostByUserIds(List<Integer> userIds) throws PostException, UserException;

    String savePost(Integer postId, Integer userId) throws UserException, PostException;

    String unsavePost(Integer postId, Integer userId) throws UserException, PostException;

    Post likePost(Integer postId, Integer userId) throws UserException, PostException;

    Post unlikePost(Integer postId, Integer userId) throws UserException, PostException;


}
