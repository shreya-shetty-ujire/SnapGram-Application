package com.snapgram.backend.repository;

import com.snapgram.backend.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {
}
