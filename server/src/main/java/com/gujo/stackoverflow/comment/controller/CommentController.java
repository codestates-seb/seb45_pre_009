package com.gujo.stackoverflow.comment.controller;

import com.gujo.stackoverflow.answer.service.AnswerService;
import com.gujo.stackoverflow.comment.dto.CommentDto;
import com.gujo.stackoverflow.comment.entity.Comment;
import com.gujo.stackoverflow.comment.mapper.CommentMapper;
import com.gujo.stackoverflow.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/questions/{question-id}/answers/{answer-id}/comments")
public class CommentController {

    private CommentService service;
    private AnswerService answerService;
    private CommentMapper mapper;

    public CommentController(AnswerService answerService, CommentService service, CommentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity postComment(@RequestBody CommentDto.PostDto postDto,
                                      @PathVariable("answer-id") Long answerId) {

        Comment comment = mapper.postDtoToComment(postDto);
        comment.setAnswer(answerService.findVerifiedAnswer(answerId));
        Comment created = service.createComment(comment);

        CommentDto.ResponseDto responseDto = mapper.commentToResponseDto(created);
        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getComments(@PathVariable("answer-id") @Positive Long anwserId) {
        List<Comment> comments = service.getComments(anwserId);

        List<CommentDto.ResponseDto> responseDtos = mapper.commentsToResponseDtos(comments);
        return new ResponseEntity(responseDtos, HttpStatus.OK);
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("comment-id") @Positive Long commentId,
                                       @RequestBody CommentDto.PatchDto patchDto) {
        Comment comment = mapper.patchDtoToComment(patchDto);
        Comment patched = service.updateComment(comment, commentId);

        CommentDto.ResponseDto responseDto = mapper.commentToResponseDto(patched);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("comment-id") @Positive Long commentId) {
        service.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{comment-id}/up")
    public ResponseEntity voteUp(@PathVariable("comment-id") @Positive Long commentId) {
        Comment voted = service.getPoint(commentId);

        CommentDto.ResponseDto responseDto = mapper.commentToResponseDto(voted);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{comment-id}/down")
    public ResponseEntity voteDown(@PathVariable("comment-id") @Positive Long commentId) {
        Comment voted = service.losePoint(commentId);

        CommentDto.ResponseDto responseDto = mapper.commentToResponseDto(voted);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
}
