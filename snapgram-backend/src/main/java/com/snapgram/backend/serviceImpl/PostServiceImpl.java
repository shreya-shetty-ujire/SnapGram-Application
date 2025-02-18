package com.snapgram.backend.serviceImpl;


import com.snapgram.backend.exception.PostException;
import com.snapgram.backend.exception.UserException;
import com.snapgram.backend.model.Post;
import com.snapgram.backend.model.User;
import com.snapgram.backend.repository.PostRepository;
import com.snapgram.backend.repository.UserRepository;
import com.snapgram.backend.service.PostService;
import com.snapgram.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    UserService userService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post createPost(Post post, Integer userId) throws UserException {
        User user=userService.findUserById(userId);
        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws UserException, PostException {
        Post post = getPostById(postId);
        User user = userService.findUserById(userId);
        if (post.getUser().getUserId().equals(user.getUserId())) {
            postRepository.deleteById(postId);
            return "Post deleted successfully";
        }
        return "You cannot delete another user's post";
    }

    @Override
    public List<Post> getPostsByUser(Integer userId) throws UserException {
        List<Post> posts = postRepository.findByUserId(userId);
        if (posts.isEmpty()) {
            throw new UserException("No posts yet");
        }

        // Convert List<Post> to List<PostDto> using PostDtoMapper
        return posts;
    }

    @Override
    public Post getPostById(Integer postId) throws PostException {
        return postRepository.findById(postId)
                .orElseThrow(()->new PostException("Post Not found with id: "+ postId));
    }

    @Override
    public List <Post> getAllPostByUserIds(List <Integer> userIds) throws PostException, UserException {
        List<Post> posts=postRepository.findAllPostsByUserIds(userIds);

        if(posts.isEmpty()){
            throw new PostException("No Post available");
        }
        return posts;
    }

    @Override
    public String savePost(Integer postId, Integer userId) throws UserException, PostException {
        Post post = getPostById(postId);
        User user=userService.findUserById(userId);

        if(!user.getSavedPosts().contains(post)){
            user.getSavedPosts().add(post);
            userRepository.save(user);
        }
        return "Post was saved successfully";
    }

    @Override
    public String unsavePost(Integer postId, Integer userId) throws UserException, PostException {
        Post post = getPostById(postId);
        User user=userService.findUserById(userId);

        if(user.getSavedPosts().contains(post)){
            user.getSavedPosts().remove(post);
            userRepository.save(user);
        }

        return "Post was unsaved successfully.";
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws UserException, PostException {
        Post post = getPostById(postId);
        User user=userService.findUserById(userId);

        post.getLikes().add(user);
        return postRepository.save(post);
    }

    @Override
    public Post unlikePost(Integer postId, Integer userId) throws UserException, PostException {
        Post post = getPostById(postId);
        User user=userService.findUserById(userId);

        post.getLikes().remove(user);
        return postRepository.save(post);
    }
}
