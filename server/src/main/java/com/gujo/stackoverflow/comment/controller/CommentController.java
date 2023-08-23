package com.gujo.stackoverflow.comment.controller;

import com.gujo.stackoverflow.answer.service.AnswerService;
import com.gujo.stackoverflow.comment.dto.CommentDto;
import com.gujo.stackoverflow.comment.entity.Comment;
import com.gujo.stackoverflow.comment.mapper.CommentMapper;
import com.gujo.stackoverflow.comment.service.CommentService;
import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/questions/{question-id}/answers/{answer-id}/comments")
@Api(tags = "댓글 API")
public class CommentController {

    private CommentService service;
    private AnswerService answerService;
    private MemberService memberService;
    private CommentMapper mapper;

    public CommentController(AnswerService answerService, CommentService service, MemberService memberService, CommentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
        this.answerService = answerService;
        this.memberService = memberService;
    }

    @PostMapping
    @ApiOperation(value = "댓글 등록", notes = "작성된 답변에 댓글작성이 가능합니다.")
    public ResponseEntity postComment(@RequestBody CommentDto.PostDto postDto,
                                      @PathVariable("answer-id") Long answerId) {

        Comment comment = mapper.postDtoToComment(postDto);
        comment.setAnswer(answerService.findVerifiedAnswer(answerId));

        Member loginMember = memberService.findLoginMember();
        comment.setMember(loginMember);

        Comment created = service.createComment(comment);

        CommentDto.ResponseDto responseDto = mapper.commentToResponseDto(created);
        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "댓글 조회", notes = "댓글 조회가 가능합니다.")
    public ResponseEntity getComments(@PathVariable("answer-id") @Positive Long anwserId) {
        List<Comment> comments = service.getComments(anwserId);

        List<CommentDto.ResponseDto> responseDtos = mapper.commentsToResponseDtos(comments);
        return new ResponseEntity(responseDtos, HttpStatus.OK);
    }

    @PatchMapping("/{comment-id}")
    @ApiOperation(value = "댓글 수정", notes = "댓글의 제목과 내용 수정이 가능합니다.")
    public ResponseEntity patchComment(@PathVariable("comment-id") @Positive Long commentId,
                                       @RequestBody CommentDto.PatchDto patchDto) {
        Comment comment = mapper.patchDtoToComment(patchDto);
        Comment patched = service.updateComment(comment, commentId);

        CommentDto.ResponseDto responseDto = mapper.commentToResponseDto(patched);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    @ApiOperation(value = "댓글 삭제", notes = "댓글 삭제가 가능합니다.")
    public ResponseEntity deleteComment(@ApiParam(value = "댓글 Id") @PathVariable("comment-id") @Positive Long commentId) {
        service.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{comment-id}/up")
    @ApiOperation(value = "댓글 추천(Up)")
    public ResponseEntity voteUp(@PathVariable("comment-id") @Positive Long commentId) {
        Comment voted = service.getPoint(commentId);

        CommentDto.ResponseDto responseDto = mapper.commentToResponseDto(voted);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{comment-id}/down")
    @ApiOperation(value = "댓글 추천(Down)")
    public ResponseEntity voteDown(@PathVariable("comment-id") @Positive Long commentId) {
        Comment voted = service.losePoint(commentId);

        CommentDto.ResponseDto responseDto = mapper.commentToResponseDto(voted);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
}
