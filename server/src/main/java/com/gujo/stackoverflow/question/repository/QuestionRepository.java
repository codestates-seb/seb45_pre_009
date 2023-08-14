package com.gujo.stackoverflow.question.repository;

import com.gujo.stackoverflow.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
