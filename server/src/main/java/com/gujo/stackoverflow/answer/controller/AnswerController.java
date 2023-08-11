package com.gujo.stackoverflow.answer.controller;

import com.gujo.stackoverflow.answer.dto.AnswerDto;
import com.gujo.stackoverflow.answer.entity.Answer;
import com.gujo.stackoverflow.answer.mapper.AnswerMapper;
import com.gujo.stackoverflow.answer.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper mapper;

    public AnswerController(AnswerService answerService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postAnswer(@RequestBody AnswerDto.PostDto postDto) {
        Answer answer = answerService.createAnswer(mapper.answerPostDtoToAnswer(postDto));

        return new ResponseEntity<>(mapper.answerToAnswerResponseDto(answer), HttpStatus.CREATED);
    }

    @PatchMapping("/{answerId}")
    public ResponseEntity patchAnswer(@PathVariable("answerId") Long answerId,
                                      @RequestBody AnswerDto.PatchDto patchDto) {
        Answer answer = answerService.updateAnswer(mapper.answerPatchDtoToAnswer(patchDto));

        return new ResponseEntity(mapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
    }

    @PatchMapping("/{answerId}/up")
    private ResponseEntity voteUp(@PathVariable("answerId") Long answerId) {
        Answer answer = answerService.getPoint(answerId);

        return new ResponseEntity(mapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
    }

    @PatchMapping("/{answerId}/down")
    private ResponseEntity voteDown(@PathVariable("answerId") Long answerId) {
        Answer answer = answerService.losePoint(answerId);

        return new ResponseEntity(mapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
    }

    @GetMapping("/{answerId}")
    public ResponseEntity getAnswer(@PathVariable("answerId") Long answerId) {
        Answer answer = answerService.findAnswer(answerId);

        return new ResponseEntity(mapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
    }

    // 이거 먼가 단단히 잘못한 거 같아요....
    @GetMapping
    public ResponseEntity getAnswers() {
        List<Answer> answers = answerService.findAnswers();

        return new ResponseEntity(mapper.answersToAnswerResponseDtos(answers), HttpStatus.OK);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable("answerId") Long answerId) {
        answerService.deleteAnswer(answerId);

        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
