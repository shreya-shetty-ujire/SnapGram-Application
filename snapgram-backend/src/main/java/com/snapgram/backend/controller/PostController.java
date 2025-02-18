package com.snapgram.backend.controller;



import com.snapgram.backend.model.Post;
import com.snapgram.backend.model.User;
import com.snapgram.backend.response.MessageResponse;
import com.snapgram.backend.service.PostService;
import com.snapgram.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPostHandler(@RequestBody Post post,
                                                   @RequestHeader("Authorization") String token){
        User user= userService.findUserProfile(token);
        Post createdPost= postService.createPost(post, user.getUserId());
        return new ResponseEntity<>(createdPost, HttpStatus.OK);
    }

    @GetMapping("/getPost/{userId}")
    public ResponseEntity<List<Post>> getPostByUserIdHandler(@PathVariable Integer userId){
        List <Post> posts= postService.getPostsByUser(userId);
        return new ResponseEntity <>(posts,HttpStatus.OK);
    }

//    All posts of following users
    @GetMapping("/following/{userIds}")
    public ResponseEntity<List<Post>> getAllPostByUserIdsHandler(@PathVariable("userIds") List<Integer> userIds){
        List <Post> posts= postService.getAllPostByUserIds(userIds);
        return new ResponseEntity <>(posts,HttpStatus.OK);
    }

    @GetMapping("/getPostFromId/{postId}")
    public ResponseEntity<Post> getPostByPostIdhandler(@PathVariable Integer postId){
        Post post= postService.getPostById(postId);
        return new ResponseEntity <>(post,HttpStatus.OK);
    }

    @PutMapping("/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,
                                                @RequestHeader("Authorization") String token){
        User user= userService.findUserProfile(token);
        Post likedPost= postService.likePost(postId,user.getUserId());
        return new ResponseEntity <>(likedPost,HttpStatus.OK);
    }

    @PutMapping("/unlike/{postId}")
    public ResponseEntity<Post> unlikePostHandler(@PathVariable Integer postId,
                                                @RequestHeader("Authorization") String token){
        User user= userService.findUserProfile(token);
        Post likedPost= postService.unlikePost(postId,user.getUserId());
        return new ResponseEntity <>(likedPost,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<MessageResponse> deletePostHandler(@PathVariable Integer postId,
                                                           @RequestHeader("Authorization") String token){
        User user= userService.findUserProfile(token);
        String message=postService.deletePost(postId,user.getUserId());
        MessageResponse messageResponse=new MessageResponse(message);
        return new ResponseEntity <>(messageResponse,HttpStatus.OK);
    }

    @PutMapping("/save/{postId}")
    public ResponseEntity<MessageResponse> savePostHandler(@PathVariable Integer postId,
                                                           @RequestHeader("Authorization") String token){
        User user= userService.findUserProfile(token);
        String message=postService.savePost(postId,user.getUserId());
        MessageResponse messageResponse=new MessageResponse(message);
        return new ResponseEntity <>(messageResponse,HttpStatus.OK);
    }
    @PutMapping("/unsave/{postId}")
    public ResponseEntity<MessageResponse> unsavePostHandler(@PathVariable Integer postId,
                                                             @RequestHeader("Authorization") String token){
        User user= userService.findUserProfile(token);
        String message=postService.unsavePost(postId,user.getUserId());
        MessageResponse messageResponse=new MessageResponse(message);
        return new ResponseEntity <>(messageResponse,HttpStatus.OK);
    }

}
