package com.gujo.stackoverflow.comment.service;

import com.gujo.stackoverflow.comment.entity.Comment;
import com.gujo.stackoverflow.comment.repository.CommentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
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
    public List<Comment> getComments(Long anwserId) {
        return repository.findByAnswer(anwserId);
    }

    @Transactional
    public Comment updateComment(Comment comment, Long commentId) {
        Comment findComment = repository.findById(commentId).orElseThrow();

        Optional.ofNullable(comment.getContent()).ifPresent(findComment::setContent);

        findComment.setModifiedAt(LocalDateTime.now());
        return findComment;
    }

    public void deleteComment(Long commentId) {
        repository.deleteById(commentId);
    }
}
