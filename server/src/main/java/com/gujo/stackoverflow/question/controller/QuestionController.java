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
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    private final QuestionMapper mapper;

    public QuestionController(QuestionService questionService, QuestionMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionDto.PostDto postDto) {
        Question question = mapper.questionPostDtoToQuestion(postDto);
        Question createdQuestion = questionService.createQuestion(question);
        QuestionDto.ResponseDto responseQuestion = mapper.questionToQuestionResponseDto(createdQuestion);
        return new ResponseEntity(responseQuestion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getQuestions(Pageable pageable) {
        List<Question> questions = questionService.getQuestions(pageable);

//        전체 질문 조회 시 게시물의 제목 이름 등 표시 -> 내용 미포함
//        List<Question>으로 질문들을 받아오고 반복자를 활용해 전체질문 조회 요청용 응답 DTO로 변환
        List<QuestionDto.getQuestionsResponseDto> result = new ArrayList<>();
        for (Question question : questions) {
            result.add(mapper.questionToGetQuestionsResponseDto(question));
        }

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") Long questionId) {
        Question question = questionService.getQuestion(questionId);
        QuestionDto.ResponseDto responseQuestion = mapper.questionToQuestionResponseDto(question);
        return new ResponseEntity(responseQuestion, HttpStatus.OK);
    }
}
