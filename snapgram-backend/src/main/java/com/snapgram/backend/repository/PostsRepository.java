package com.snapgram.backend.repository;


import com.snapgram.backend.model.Posts;
import com.snapgram.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

    //Find all posts of a particular user
    List <Posts> findByUser(User user);


}
