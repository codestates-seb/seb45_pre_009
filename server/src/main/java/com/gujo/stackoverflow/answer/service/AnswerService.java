package com.gujo.stackoverflow.answer.service;

import com.gujo.stackoverflow.answer.entity.Answer;
import com.gujo.stackoverflow.answer.repository.AnswerRepository;
import com.gujo.stackoverflow.exception.BusinessLogicException;
import com.gujo.stackoverflow.exception.ExceptionCode;
import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final MemberService memberService;

    public AnswerService(AnswerRepository answerRepository, MemberService memberService) {
        this.answerRepository = answerRepository;
        this.memberService = memberService;
    }

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        memberService.checkLoginMemberHasAuthority(findAnswer.getMember().getMemberId());

        Optional.ofNullable(answer.getContent())
                .ifPresent(content -> findAnswer.setContent(content));

        // 수정시간 반영
        findAnswer.setModifiedAt(LocalDateTime.now());

        return findAnswer;
    }

    @Transactional(readOnly = true)
    public Answer findAnswer(Long answerId) {
        Answer answer = findVerifiedAnswer(answerId);

        if( answer.getAnswerStatus() == Answer.AnswerStatus.ANSWER_EXIST ) {
            return answer;
        } else throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND);
    }

    @Transactional(readOnly = true)
    public List<Answer> findAnswers() {
        List<Answer> allAnswers = answerRepository.findAll();

        return allAnswers.stream()
                .filter(answer -> answer.getAnswerStatus() == Answer.AnswerStatus.ANSWER_EXIST)
                .collect(Collectors.toList());
    }

    public void deleteAnswer(Long answerId) {
        Answer answer = findVerifiedAnswer(answerId);
        memberService.checkLoginMemberHasAuthority(answer.getMember().getMemberId());

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

        memberService.vote(findAnswer.getMember(), 10L);

        findAnswer.setPoint(findAnswer.getPoint() + 1);
        return findAnswer;
    }

    public Answer losePoint(Long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);

        Member postMember = memberService.vote(findAnswer.getMember(), -2L);

//        답변에 비추천을 할 경우 -1
        if (postMember.getReputation() > 2) {
            postMember.setReputation(postMember.getReputation() - 1);
        }

        findAnswer.setPoint(findAnswer.getPoint() - 1);
        return findAnswer;
    }
}
