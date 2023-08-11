package com.gujo.stackoverflow.answer.service;

import com.gujo.stackoverflow.answer.entity.Answer;
import com.gujo.stackoverflow.answer.repository.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getContent())
                .ifPresent(content -> findAnswer.setContent(content));

        // 수정시간 반영
        findAnswer.setModifiedAt(LocalDateTime.now());

        return findAnswer;
    }

    public Answer findAnswer(Long answerId) {
        return findVerifiedAnswer(answerId);
    }

    public List<Answer> findAnswers() {
        return answerRepository.findAll();
    }

    public void deleteAnswer(Long answerId) {
        Answer answer = findVerifiedAnswer(answerId);
        answerRepository.delete(answer);
    }

    public Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer answer = optionalAnswer.orElseThrow();       // 예외처리 아직 ....

        return answer;
    }
}
