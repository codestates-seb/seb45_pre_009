package com.gujo.stackoverflow.answer.controller;

import com.gujo.stackoverflow.answer.dto.AnswerDto;
import com.gujo.stackoverflow.answer.entity.Answer;
import com.gujo.stackoverflow.answer.mapper.AnswerMapper;
import com.gujo.stackoverflow.answer.service.AnswerService;
import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.member.service.MemberService;
import com.gujo.stackoverflow.question.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/questions/{question-id}/answers")
@Api(tags = "답변 API")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final MemberService memberService;

    private final AnswerMapper mapper;

    public AnswerController(AnswerService answerService, QuestionService questionService, MemberService memberService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.questionService = questionService;
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    @ApiOperation(value = "답변 등록", notes = "작성된 질문글에 답변작성이 가능합니다.")
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.PostDto postDto,
                                     @PathVariable("question-id") Long questionId) {

        Answer answer = mapper.answerPostDtoToAnswer(postDto);
        answer.setQuestion(questionService.findVerifiedQuestion(questionId));
        Member loginMember = memberService.findLoginMember();
        answer.setMember(loginMember);

        Answer response = answerService.createAnswer(answer);

        return new ResponseEntity<>(mapper.answerToAnswerResponseDto(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{answerId}")
    @ApiOperation(value = "답변 수정", notes = "작성된 답변에 내용 수정이 가능합니다.")
    public ResponseEntity patchAnswer(@PathVariable("answerId") @Positive Long answerId,
                                      @RequestBody AnswerDto.PatchDto patchDto) {
        Answer answer = answerService.updateAnswer(mapper.answerPatchDtoToAnswer(patchDto));

        return new ResponseEntity(mapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
    }

    @PatchMapping("/{answerId}/up")
    @ApiOperation(value = "답변 추천(Up)")
    private ResponseEntity voteUp(@PathVariable("answerId") @Positive Long answerId) {
        Answer answer = answerService.getPoint(answerId);

        return new ResponseEntity(mapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
    }

    @PatchMapping("/{answerId}/down")
    @ApiOperation(value = "답변 추천(Down)")
    private ResponseEntity voteDown(@Valid @PathVariable("answerId") @Positive Long answerId) {
        Answer answer = answerService.losePoint(answerId);

        return new ResponseEntity(mapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
    }

    @GetMapping("/{answerId}")
    @ApiOperation(value = "답변 상세 조회", notes = "답변Id로 상세 조회가 가능합니다.")
    public ResponseEntity getAnswer(@ApiParam(value = "답변 Id") @PathVariable("answerId") @Positive Long answerId) {
        Answer answer = answerService.findAnswer(answerId);

        return new ResponseEntity(mapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "답변 조회", notes = "답변 조회가 가능합니다.")
    public ResponseEntity getAnswers() {
        List<Answer> answers = answerService.findAnswers();

        return new ResponseEntity(mapper.answersToAnswerResponseDtos(answers), HttpStatus.OK);
    }

    @DeleteMapping("/{answerId}")
    @ApiOperation(value = "답변 삭제", notes = "답변 삭제가 가능합니다.")
    public ResponseEntity deleteAnswer(@ApiParam(value = "답변 Id") @PathVariable("answerId") @Positive Long answerId) {
        answerService.deleteAnswer(answerId);

        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
