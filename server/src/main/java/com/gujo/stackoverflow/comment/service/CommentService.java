package com.gujo.stackoverflow.comment.service;

import com.gujo.stackoverflow.comment.entity.Comment;
import com.gujo.stackoverflow.comment.repository.CommentRepository;
import com.gujo.stackoverflow.exception.BusinessLogicException;
import com.gujo.stackoverflow.exception.ExceptionCode;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {

    private CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public Comment createComment(Comment comment) {
        return repository.save(comment);
    }

//    Answer 기능 구현 후 리팩토링 필요
//    AnswerService 의존성 주입 필요??
    @Transactional(readOnly = true)
    public List<Comment> getComments(Long anwserId) {
        return repository.findByAnswer(anwserId);
    }

    public Comment updateComment(Comment comment, Long commentId) {
        Comment findComment = findVeridiedComment(commentId);

        findComment.setContent(comment.getContent());

        findComment.setModifiedAt(LocalDateTime.now());
        return findComment;
    }

    public void deleteComment(Long commentId) {
        findVeridiedComment(commentId);

        repository.deleteById(commentId);
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
