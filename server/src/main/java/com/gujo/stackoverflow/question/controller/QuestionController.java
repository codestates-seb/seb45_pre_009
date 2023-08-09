package com.gujo.stackoverflow.question.controller;

import com.gujo.stackoverflow.question.dto.QuestionDto;
import com.gujo.stackoverflow.question.entity.Question;
import com.gujo.stackoverflow.question.mapper.QuestionMapper;
import com.gujo.stackoverflow.question.service.QuestionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    private QuestionMapper mapper;

    public QuestionController(QuestionMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionDto.PostDto postDto) {
        Question question = mapper.questionPostDtoToQuestion(postDto);
        Question createdQuestion = questionService.createQuestion(question);
        return new ResponseEntity(createdQuestion, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity getQuestions(Pageable pageable) {
        List<Question> questions = questionService.getAllQuestions(pageable);
        return new ResponseEntity(questions, HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") Long questionId) {
        Question findQuestion = questionService.getQuestion(questionId);
        return new ResponseEntity(findQuestion, HttpStatus.OK);
    }
}
