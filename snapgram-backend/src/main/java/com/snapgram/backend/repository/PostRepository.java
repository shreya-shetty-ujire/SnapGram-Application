package com.snapgram.backend.repository;


import com.snapgram.backend.model.Comments;
import com.snapgram.backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p WHERE p.user.userId=?1")
    List <Post> findByUserId(Integer userId);

    @Query("SELECT p FROM Post p WHERE p.user.userId IN (:users) ORDER BY p.createdAt DESC")
    List<Post> findAllPostsByUserIds(@Param("users") List<Integer> userIds);

    Optional <Post> findByCommentsContaining(Comments comment);

}
