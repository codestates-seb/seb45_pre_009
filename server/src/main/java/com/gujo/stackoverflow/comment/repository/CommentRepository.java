package com.gujo.stackoverflow.comment.repository;

import com.gujo.stackoverflow.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM COMMENT WHERE ANSWER_ID = :answerId", nativeQuery = true)
    List<Comment> findByAnswer(Long answerId);
}
