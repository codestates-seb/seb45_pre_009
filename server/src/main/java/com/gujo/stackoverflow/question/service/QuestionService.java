package com.gujo.stackoverflow.question.service;

import com.gujo.stackoverflow.question.entity.Question;
import com.gujo.stackoverflow.question.repository.QuestionRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionService {
    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Question createQuestion(Question question) {
        return repository.save(question);
    }

    @Transactional(readOnly = true)
    public List<Question> getQuestions(Pageable pageable) {

//        검색 시 내용 글자수 200자 제한..
//        List<Question> questions = repository.findAll();
//        List<Question> result = new ArrayList<>();
//        for(Question question : questions) {
//            if (question.getContent().length() >= 200) {
//                question.setContent(question.getContent().substring(0, 200));
//            }
//            result.add(question);
//        }
//        return result;

        return repository.findAll(pageable).getContent();
    }

    public Question getQuestion(Long questionId) {
        Question question = repository.findById(questionId).orElseThrow();
        question.setViews(question.getViews() + 1);
        return question;
    }

    public Question updateQuestion(Long questionId, Question question) {
        Question findQuestion = repository.findById(questionId).orElseThrow();

        Optional.ofNullable(question.getTitle()).ifPresent(findQuestion::setTitle);
        Optional.ofNullable(question.getContent()).ifPresent(findQuestion::setContent);

//        수정시간 반영
        findQuestion.setModifiedAt(LocalDateTime.now());

        return findQuestion;
    }

    public void deleteQuestion(Long questionId) {
        repository.deleteById(questionId);
    }

    public Question getPoint(Long questionId) {
        Question question = repository.findById(questionId).orElseThrow();
        question.setPoint(question.getPoint() + 1);
        return question;
    }

    public Question losePoint(Long questionId) {
        Question question = repository.findById(questionId).orElseThrow();
        question.setPoint(question.getPoint() - 1);
        return question;
    }
}
