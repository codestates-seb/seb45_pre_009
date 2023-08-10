package com.gujo.stackoverflow.question.service;

import com.gujo.stackoverflow.question.entity.Question;
import com.gujo.stackoverflow.question.repository.QuestionRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Transactional // repository.save 하지 않아도 DB 반영됨
    public Question updateQuestion(Long questionId, Question question) {
        Question findQuestion = repository.findById(questionId).orElseThrow();

//        patchDto에 title, content 각각 항목에 값이 null이 아닐경우 수정사항 반영
//        if(question.getTitle() != null) {
//            findQuestion.setTitle(question.getTitle());
//        }
//        if (question.getContent() != null) {
//            findQuestion.setContent(question.getContent());
//        }
        Optional.ofNullable(question.getTitle()).ifPresent(findQuestion::setTitle);
        Optional.ofNullable(question.getContent()).ifPresent(findQuestion::setContent);

//        수정시간 반영
        findQuestion.setModifiedAt(LocalDateTime.now());

        return findQuestion;
    }

    public void deleteQuestion(Long questionId) {
        repository.deleteById(questionId);
    }

    @Transactional
    public Question getPoint(Long questionId) {
        Question question = repository.findById(questionId).orElseThrow();
        question.setPoint(question.getPoint() + 1);
        return question;
    }

    @Transactional
    public Question losePoint(Long questionId) {
        Question question = repository.findById(questionId).orElseThrow();
        question.setPoint(question.getPoint() - 1);
        return question;
    }
}
