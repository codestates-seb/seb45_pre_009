package com.gujo.stackoverflow.question.controller;

import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.member.service.MemberService;
import com.gujo.stackoverflow.question.dto.QuestionDto;
import com.gujo.stackoverflow.question.entity.Question;
import com.gujo.stackoverflow.question.mapper.QuestionMapper;
import com.gujo.stackoverflow.question.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin
public class QuestionController {

    private final MemberService memberService;
    private final QuestionService service;
    private final QuestionMapper mapper;

    public QuestionController(MemberService memberService, QuestionService questionService, QuestionMapper mapper) {
        this.memberService = memberService;
        this.service = questionService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.PostDto postDto) {
        Question question = mapper.postDtoToQuestion(postDto);

        Member loginMember = memberService.findLoginMember();
        question.setMember(loginMember);

        Question created = service.createQuestion(question);

        QuestionDto.ResponseDto responseDto = mapper.questionToResponseDto(created);
        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

//    메인 화면 표시용 질문글 제목 이름 등 표시 -> 내용 미포함
    @GetMapping("/main")
    public ResponseEntity getQuestionsWithoutContent(Pageable pageable) {
        List<Question> questions = service.getQuestionsWithoutContent(pageable);

        List<QuestionDto.getAllResponseDto> result = mapper.questionToGetAllResponseDto(questions);
        return new ResponseEntity(result, HttpStatus.OK);
    }

//    검색 시 내용 글자 100만 표시
    @GetMapping
    public ResponseEntity getQuestionsWithPreview(Pageable pageable) {
        Page<Question> questions = service.getQuestionsWithPreview(pageable);

        List<QuestionDto.ResponseDto> result = mapper.questionsToResponseDtos(questions);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") @Positive Long questionId) {
        Question question = service.getQuestion(questionId);

        QuestionDto.ResponseDto responseDto = mapper.questionToResponseDto(question);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive Long questionId,
                                        @Valid @RequestBody QuestionDto.PatchDto patchDto) {
        Question question = mapper.patchDtoToQuestion(patchDto);
        Question updated = service.updateQuestion(questionId, question);

        QuestionDto.ResponseDto responseDto = mapper.questionToResponseDto(updated);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Positive Long questionId) {
        service.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    추천
    @PatchMapping("/{question-id}/up")
    public ResponseEntity voteUp(@PathVariable("question-id") @Positive Long questionId) {
        Question voted = service.getPoint(questionId);

        QuestionDto.ResponseDto responseDto = mapper.questionToResponseDto(voted);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

//    비추천
    @PatchMapping("/{question-id}/down")
    public ResponseEntity voteDown(@PathVariable("question-id") @Positive Long questionId) {
        Question voted = service.losePoint(questionId);

        QuestionDto.ResponseDto responseDto = mapper.questionToResponseDto(voted);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
  
    //    검색
    @GetMapping("/search/questions")
    public ResponseEntity<Page<QuestionDto.ResponseDto>> searchQuestions(@PageableDefault Pageable pageable,
                                                                         @RequestParam(required = false, defaultValue = "")
                                                                         String keyword) {
        Page<Question> searchResult = service.questionSearchList(keyword, keyword, pageable);
        Page<QuestionDto.ResponseDto> responsePage = searchResult.map(
                question -> mapper.questionToResponseDto(question));

        return ResponseEntity.ok(responsePage);
    }
}
