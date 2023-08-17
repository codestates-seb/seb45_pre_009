package com.gujo.stackoverflow.question.repository;

import com.gujo.stackoverflow.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
