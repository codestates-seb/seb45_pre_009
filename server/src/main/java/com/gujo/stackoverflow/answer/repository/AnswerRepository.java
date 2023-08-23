package com.gujo.stackoverflow.answer.repository;

import com.gujo.stackoverflow.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(value = "SELECT * FROM Answer WHERE QUESTION_ID = :questionId", nativeQuery =true)
    List<Answer> findByQuestion(Long questionId);
}
