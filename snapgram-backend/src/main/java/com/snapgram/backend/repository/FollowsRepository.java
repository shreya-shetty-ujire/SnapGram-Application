package com.snapgram.backend.repository;

import com.snapgram.backend.model.Follows;
import com.snapgram.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowsRepository extends JpaRepository<Follows,Long> {

    List <Follows> findByFollowing(User user);

    List<Follows> findByFollower(User user);
}
