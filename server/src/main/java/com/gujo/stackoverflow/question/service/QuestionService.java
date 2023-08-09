package com.gujo.stackoverflow.question.service;

import com.gujo.stackoverflow.question.entity.Question;
import com.gujo.stackoverflow.question.repository.QuestionRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Question createQuestion(Question question) {
        return repository.save(question);
    }

    public List<Question> getQuestions(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public Question getQuestion(Long questionId) {
        return repository.findById(questionId).orElseThrow();
    }
}
