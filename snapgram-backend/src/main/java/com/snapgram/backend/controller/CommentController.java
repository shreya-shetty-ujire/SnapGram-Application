package com.snapgram.backend.controller;


import com.snapgram.backend.exception.CommentException;
import com.snapgram.backend.model.Comments;
import com.snapgram.backend.model.User;
import com.snapgram.backend.response.MessageResponse;
import com.snapgram.backend.service.CommentService;
import com.snapgram.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/create/{postId}")
    public ResponseEntity <Comments> createCommentHandler(@RequestBody Comments comment,
                                                          @PathVariable("postId") Integer postId,
                                                          @RequestHeader("Authorization") String token ) throws CommentException {
        User user= userService.findUserProfile(token);
        Comments createdComment=commentService.createComment(comment,postId, user.getUserId());
        return new ResponseEntity <>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity <List<Comments>> createCommentHandler(@PathVariable("postId") Integer postId,
                                                          @RequestHeader("Authorization") String token ) throws CommentException {
        List <Comments > comments= commentService.findCommentsByPostId(postId);
        return new ResponseEntity <>(comments, HttpStatus.OK);
    }

    @PutMapping("/like/{commentId}")
    public ResponseEntity <Comments> likeCommentHandler(@PathVariable("commentId") Integer commentId,
                                                          @RequestHeader("Authorization") String token ) throws CommentException {
        User user= userService.findUserProfile(token);
        Comments comment=commentService.likeComment(commentId,user.getUserId());
        return new ResponseEntity <>(comment, HttpStatus.OK);
    }
    @PutMapping("/unlike/{commentId}")
    public ResponseEntity <Comments> unlikeCommentHandler(@PathVariable("commentId") Integer commentId,
                                                          @RequestHeader("Authorization") String token ) throws CommentException {
        User user= userService.findUserProfile(token);
        Comments comment=commentService.unlikeComment(commentId,user.getUserId());
        return new ResponseEntity <>(comment, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity <MessageResponse> deleteCommentHandler(@PathVariable("commentId") Integer commentId,
                                                                 @RequestHeader("Authorization") String token ) throws CommentException {
        User user= userService.findUserProfile(token);
        Comments comment=commentService.findCommentById(commentId);
        String message=commentService.deleteComment(commentId,comment.getPost().getPostId(),user.getUserId());
        MessageResponse messageResponse=new MessageResponse(message);
        return new ResponseEntity <>(messageResponse, HttpStatus.OK);
    }
}
