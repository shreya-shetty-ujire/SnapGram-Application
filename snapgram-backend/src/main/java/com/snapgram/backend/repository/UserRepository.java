package com.snapgram.backend.repository;

import com.snapgram.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA automatically generates the query for these methods
    Optional <User> findByUserName(String username);
    Optional <User> findByEmail(String email);
}
