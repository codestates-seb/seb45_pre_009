package com.gujo.stackoverflow.comment.controller;

import com.gujo.stackoverflow.comment.dto.CommentDto;
import com.gujo.stackoverflow.comment.entity.Comment;
import com.gujo.stackoverflow.comment.mapper.CommentMapper;
import com.gujo.stackoverflow.comment.service.CommentService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService service;
    private CommentMapper mapper;

    public CommentController(CommentService service, CommentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postComment(@RequestBody CommentDto.PostDto postDto) {
        Comment comment = mapper.postDtoToComment(postDto);
        Comment created = service.createComment(comment);

        CommentDto.ResponseDto responseDto = mapper.commentToResponseDto(created);
        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

//    구현 어떻게..???
    @GetMapping("/{answer-id}")
    public ResponseEntity getComments(@PathVariable("answer-id") Long anwserId) {
        List<Comment> comments = service.getComments(anwserId);

        List<CommentDto.ResponseDto> responseDtos = mapper.commentsToResponseDtos(comments);
        return new ResponseEntity(responseDtos, HttpStatus.OK);
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("comment-id") Long commentId,
                                       @RequestBody CommentDto.PatchDto patchDto) {
        Comment comment = mapper.patchDtoToComment(patchDto);
        Comment patched = service.updateComment(comment, commentId);

        CommentDto.ResponseDto responseDto = mapper.commentToResponseDto(patched);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("comment-id") Long commentId) {
        service.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
