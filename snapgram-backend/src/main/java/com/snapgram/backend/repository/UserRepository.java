package com.snapgram.backend.repository;

import com.snapgram.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Spring Data JPA automatically generates the query for these methods
     Optional <User> findByUsername(String username);
     Optional <User> findByEmail(String email);

     @Query("SELECT u FROM User u WHERE u.userId IN :users")
     List <User> findAllUsersByUserIds(@Param("users") List<Integer> userIds);   // Fetching Followers / Following List

    // Search users
    @Query("SELECT DISTINCT u FROM User u WHERE u.username LIKE %:queryParam% OR u.email LIKE %:queryParam% OR u.name LIKE %:queryParam%")
    List<User> findByQuery(@Param("queryParam") String query);
}
