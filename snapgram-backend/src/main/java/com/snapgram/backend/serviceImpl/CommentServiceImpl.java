package com.snapgram.backend.serviceImpl;


import com.snapgram.backend.exception.CommentException;
import com.snapgram.backend.exception.PostException;
import com.snapgram.backend.exception.UserException;
import com.snapgram.backend.model.Comments;
import com.snapgram.backend.model.Post;
import com.snapgram.backend.model.User;
import com.snapgram.backend.repository.CommentsRepository;
import com.snapgram.backend.repository.PostRepository;
import com.snapgram.backend.service.CommentService;
import com.snapgram.backend.service.PostService;
import com.snapgram.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Override
    public Comments createComment(Comments comment, Integer postId, Integer userId) throws UserException, PostException {
        User user=userService.findUserById(userId);
        Post post=postService.getPostById(postId);

        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setPost(post);
        Comments saveComment=commentsRepository.save(comment);

        post.getComments().add(saveComment);
        postRepository.save(post);
        return saveComment;
    }

    @Override
    public List<Comments> findCommentsByPostId(Integer postId) throws CommentException, PostException {
        Post post=postService.getPostById(postId);
        List <Comments> comments = commentsRepository.findByPost(post);

        // If no comments found, throw an exception
        if (comments.isEmpty()) {
            throw new CommentException("No comments found for this post!");
        }

        return comments;
    }

    @Override
    public Comments findCommentById(Integer commentId) throws CommentException {
        return commentsRepository.findById(commentId)
                .orElseThrow(()->new CommentException("Comment not found!"));
    }

    @Override
    public Comments likeComment(Integer commentId, Integer userId) throws CommentException, UserException {
        User user=userService.findUserById(userId);
        Comments comment = findCommentById(commentId);

        comment.getLikes().add(user);

        return commentsRepository.save(comment);
    }

    @Override
    public Comments unlikeComment(Integer commentId, Integer userId) throws CommentException, UserException {
        User user=userService.findUserById(userId);
        Comments comment = findCommentById(commentId);

        comment.getLikes().remove(user);

        return commentsRepository.save(comment);
    }

    @Override
    public String deleteComment(Integer commentId, Integer postId, Integer userId) throws CommentException,
            UserException {
        Comments comment=commentsRepository.findById(commentId)
                .orElseThrow(()->new CommentException("Comment not found!"));
        Post post=postService.getPostById(postId);
        if(comment.getUser().getUserId().equals(userId) || post.getUser().getUserId().equals(userId)){
            post.getComments().remove(comment);
            postRepository.save(post);
            commentsRepository.delete(comment);
            return "Post deleted successfully";
        }
        return "You cannot delete the comment";

    }
}
