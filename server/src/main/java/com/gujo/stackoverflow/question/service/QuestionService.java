package com.gujo.stackoverflow.question.service;

import com.gujo.stackoverflow.exception.BusinessLogicException;
import com.gujo.stackoverflow.exception.ExceptionCode;
import com.gujo.stackoverflow.member.service.MemberService;
import com.gujo.stackoverflow.question.entity.Question;
import com.gujo.stackoverflow.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionService {
    private final QuestionRepository repository;
    private final MemberService memberService;

    public QuestionService(QuestionRepository repository, MemberService memberService) {
        this.repository = repository;
        this.memberService = memberService;
    }

    public Question createQuestion(Question question) {
        return repository.save(question);
    }

    @Transactional(readOnly = true)
    public Page<Question> getQuestionsWithPreview(Pageable pageable) {

        Page<Question> questionPage = repository.findAll(pageable);
        for (Question question : questionPage) {
            if (question.getContent().length() >= 100) {
                question.setContent(question.getContent().substring(0, 100));
            }
        }
        return questionPage;
    }

    @Transactional(readOnly = true)
    public List<Question> getQuestionsWithoutContent(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public Question getQuestion(Long questionId) {
        Question question = findVerifiedQuestion(questionId);
        question.setViews(question.getViews() + 1);
        return question;
    }

    public Question updateQuestion(Long questionId, Question question) {
        Question findQuestion = findVerifiedQuestion(questionId);
        memberService.checkLoginMemberWrote(findQuestion.getMember().getMemberId());

        Optional.ofNullable(question.getTitle()).ifPresent(findQuestion::setTitle);
        Optional.ofNullable(question.getContent()).ifPresent(findQuestion::setContent);

//        수정시간 반영
        findQuestion.setModifiedAt(LocalDateTime.now());

        return findQuestion;
    }

    public void deleteQuestion(Long questionId) {
        Question findQuestion = findVerifiedQuestion(questionId);
        memberService.checkLoginMemberWrote(findQuestion.getMember().getMemberId());

        repository.deleteById(questionId);
    }

    public Question getPoint(Long questionId) {
        Question question = findVerifiedQuestion(questionId);
        question.setPoint(question.getPoint() + 1);
        return question;
    }

    public Question losePoint(Long questionId) {
        Question question = findVerifiedQuestion(questionId);
        question.setPoint(question.getPoint() - 1);
        return question;
    }

    public Question findVerifiedQuestion(Long questionId) {
        Optional<Question> findQuestion = repository.findById(questionId);

        if (findQuestion.isPresent())
            return findQuestion.get();
        else throw new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND);
    }


    // 검색 추가
    public Page<Question> questionSearchList(String title, String content, Pageable pageable) {
        return repository.findByTitleContainingOrContentContaining(title, content, pageable);
    }


}
