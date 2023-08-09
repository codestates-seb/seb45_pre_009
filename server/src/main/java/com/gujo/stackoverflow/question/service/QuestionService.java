package com.gujo.stackoverflow.question.service;

import com.gujo.stackoverflow.question.entity.Question;
import com.gujo.stackoverflow.question.repository.QuestionRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private QuestionRepository repository;

    public Question createQuestion(Question question) {
        return repository.save(question);
    }

    public List<Question> getAllQuestions(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public Question getQuestion(Long questionId) {
        return repository.findById(questionId).orElseThrow();
    }
}
