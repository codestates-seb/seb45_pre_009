package com.gujo.stackoverflow.answer.service;

import com.gujo.stackoverflow.answer.entity.Answer;
import com.gujo.stackoverflow.answer.repository.AnswerRepository;
import com.gujo.stackoverflow.exception.BusinessLogicException;
import com.gujo.stackoverflow.exception.ExceptionCode;
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

    @Transactional(readOnly = true)
    public Answer findAnswer(Long answerId) {
        return findVerifiedAnswer(answerId);
    }

    @Transactional(readOnly = true)
    public List<Answer> findAnswers() {
        return answerRepository.findAll();
    }

    public void deleteAnswer(Long answerId) {
        Answer answer = findVerifiedAnswer(answerId);
        answer.setAnswerStatus(Answer.AnswerStatus.ANSWER_NOT_EXIST);

        answerRepository.save(answer);
    }

    public Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        if (optionalAnswer.isPresent())
            return optionalAnswer.get();
        else throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND);
    }

    public Answer getPoint(Long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        findAnswer.setPoint(findAnswer.getPoint() + 1);
        return findAnswer;
    }

    public Answer losePoint(Long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        findAnswer.setPoint(findAnswer.getPoint() - 1);
        return findAnswer;
    }
}
