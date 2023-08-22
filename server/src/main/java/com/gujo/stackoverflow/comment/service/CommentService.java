package com.gujo.stackoverflow.comment.service;

import com.gujo.stackoverflow.answer.entity.Answer;
import com.gujo.stackoverflow.comment.entity.Comment;
import com.gujo.stackoverflow.comment.repository.CommentRepository;
import com.gujo.stackoverflow.exception.BusinessLogicException;
import com.gujo.stackoverflow.exception.ExceptionCode;
import com.gujo.stackoverflow.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {

    private final CommentRepository repository;
    private final MemberService memberService;

    public CommentService(CommentRepository repository, MemberService memberService) {
        this.repository = repository;
        this.memberService = memberService;
    }

    public Comment createComment(Comment comment) {
        return repository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<Comment> getComments(Long answerId) {
        return repository.findByAnswer(answerId);
    }

    public Comment updateComment(Comment comment, Long commentId) {
        Comment findComment = findVeridiedComment(commentId);
        memberService.checkLoginMemberWrote(findComment.getMember().getMemberId());

        findComment.setContent(comment.getContent());

        findComment.setModifiedAt(LocalDateTime.now());
        return findComment;
    }

    public void deleteComment(Long commentId) {
        Comment findComment = findVeridiedComment(commentId);
        memberService.checkLoginMemberWrote(findComment.getMember().getMemberId());

        findComment.setCommentStatus(Comment.CommentStatus.COMMENT_NOT_EXIST);

        repository.save(findComment);
//        repository.deleteById(commentId);
    }

    public Comment getPoint(Long commentId) {
        Comment comment = findVeridiedComment(commentId);
        comment.setPoint(comment.getPoint() + 1);

        return comment;
    }

    public Comment losePoint(Long commentId) {
        Comment comment = findVeridiedComment(commentId);
        comment.setPoint(comment.getPoint() - 1);

        return comment;
    }

    public Comment findVeridiedComment(Long commentId) {
        Optional<Comment> findComment = repository.findById(commentId);

        if (findComment.isPresent())
            return findComment.get();
        else throw new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND);
    }
}
