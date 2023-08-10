package com.gujo.stackoverflow.question.controller;

import com.gujo.stackoverflow.question.dto.QuestionDto;
import com.gujo.stackoverflow.question.entity.Question;
import com.gujo.stackoverflow.question.mapper.QuestionMapper;
import com.gujo.stackoverflow.question.service.QuestionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService service;

    private final QuestionMapper mapper;

    public QuestionController(QuestionService questionService, QuestionMapper mapper) {
        this.service = questionService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionDto.PostDto postDto) {
        Question question = mapper.postDtoToQuestion(postDto);
        Question created = service.createQuestion(question);

        QuestionDto.ResponseDto responseDto = mapper.questionToResponseDto(created);
        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getQuestions(Pageable pageable) {
        List<Question> questions = service.getQuestions(pageable);

//        전체 질문 조회 시 게시물의 제목 이름 등 표시 -> 내용 미포함
//        List<Question>으로 질문들을 받아오고 반복자를 활용해 전체질문 조회 요청용 응답 DTO로 변환
        List<QuestionDto.getAllResponseDto> result = new ArrayList<>();
        for (Question question : questions) {
            result.add(mapper.questionToGetAllResponseDto(question));
        }

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") Long questionId) {
        Question question = service.getQuestion(questionId);

        QuestionDto.ResponseDto responseDto = mapper.questionToResponseDto(question);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") Long questionId,
                                        @RequestBody QuestionDto.PatchDto patchDto) {
        Question question = mapper.patchDtoToQuestion(patchDto);
        Question updated = service.updateQuestion(questionId, question);

        QuestionDto.ResponseDto responseDto = mapper.questionToResponseDto(updated);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") Long questionId) {
        service.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
