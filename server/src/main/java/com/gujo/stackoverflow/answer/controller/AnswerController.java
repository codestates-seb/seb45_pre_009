package com.gujo.stackoverflow.answer.controller;

import com.gujo.stackoverflow.answer.dto.AnswerDto;
import com.gujo.stackoverflow.answer.entity.Answer;
import com.gujo.stackoverflow.answer.mapper.AnswerMapper;
import com.gujo.stackoverflow.answer.service.AnswerService;
import com.gujo.stackoverflow.question.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/questions/{question-id}/answers")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final AnswerMapper mapper;

    public AnswerController(AnswerService answerService, QuestionService questionService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.questionService = questionService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.PostDto postDto,
                                     @PathVariable("question-id") Long questionId) {

        Answer answer = mapper.answerPostDtoToAnswer(postDto);
        answer.setQuestion(questionService.findVerifiedQuestion(questionId));
        Answer response = answerService.createAnswer(answer);

        return new ResponseEntity<>(mapper.answerToAnswerResponseDto(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{answerId}")
    public ResponseEntity patchAnswer(@PathVariable("answerId") @Positive Long answerId,
                                      @RequestBody AnswerDto.PatchDto patchDto) {
        Answer answer = answerService.updateAnswer(mapper.answerPatchDtoToAnswer(patchDto));

        return new ResponseEntity(mapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
    }

    @PatchMapping("/{answerId}/up")
    private ResponseEntity voteUp(@PathVariable("answerId") @Positive Long answerId) {
        Answer answer = answerService.getPoint(answerId);

        return new ResponseEntity(mapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
    }

    @PatchMapping("/{answerId}/down")
    private ResponseEntity voteDown(@Valid @PathVariable("answerId") @Positive Long answerId) {
        Answer answer = answerService.losePoint(answerId);

        return new ResponseEntity(mapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
    }

    @GetMapping("/{answerId}")
    public ResponseEntity getAnswer(@PathVariable("answerId") @Positive Long answerId) {
        Answer answer = answerService.findAnswer(answerId);

        return new ResponseEntity(mapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAnswers() {
        List<Answer> answers = answerService.findAnswers();

        return new ResponseEntity(mapper.answersToAnswerResponseDtos(answers), HttpStatus.OK);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable("answerId") @Positive Long answerId) {
        answerService.deleteAnswer(answerId);

        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
