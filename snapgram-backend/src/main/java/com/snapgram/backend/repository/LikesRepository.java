package com.snapgram.backend.repository;

import com.snapgram.backend.model.Likes;
import com.snapgram.backend.model.Posts;
import com.snapgram.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    // Count likes for a specific post
    int countByPost(Posts post);

    // Check if a user liked a specific post
    boolean existsByUserAndPost(User user, Posts post);
}
