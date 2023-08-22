package com.gujo.stackoverflow.question.controller;

import com.gujo.stackoverflow.member.entity.Member;
import com.gujo.stackoverflow.member.service.MemberService;
import com.gujo.stackoverflow.question.dto.QuestionDto;
import com.gujo.stackoverflow.question.entity.Question;
import com.gujo.stackoverflow.question.mapper.QuestionMapper;
import com.gujo.stackoverflow.question.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/questions")
@Api(tags = "질문 API")
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
    @ApiOperation(value = "질문 등록", notes = "질문글을 작성 할 수 있습니다.")
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
    @ApiOperation(value = "메인 화면 출력 질문", notes = "메인 화면 표시용으로 내용은 포함되지 않습니다.")
    public ResponseEntity getQuestionsWithoutContent(Pageable pageable) {
        List<Question> questions = service.getQuestionsWithoutContent(pageable);

        List<QuestionDto.getAllResponseDto> result = mapper.questionToGetAllResponseDto(questions);
        return new ResponseEntity(result, HttpStatus.OK);
    }

//    검색 시 내용 글자 100만 표시
    @GetMapping
    @ApiOperation(value = "질문 조회", notes = "등록된 질문글을 조회가 가능합니다. <bn> 작성된 질문 내용의 100글자까지 표시됩니다.")
    public ResponseEntity getQuestionsWithPreview(Pageable pageable) {
        Page<Question> questions = service.getQuestionsWithPreview(pageable);

        List<QuestionDto.ResponseDto> result = mapper.questionsToResponseDtos(questions);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    @ApiOperation(value = "질문 상세 조회", notes = "등록된 질문글을 question Id로 상세 조히가 가능합니다.")
    public ResponseEntity getQuestion(@PathVariable("question-id") @Positive @ApiParam(value = "질문 Id") Long questionId) {
        Question question = service.getQuestion(questionId);

        QuestionDto.ResponseDto responseDto = mapper.questionToResponseDto(question);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{question-id}")
    @ApiOperation(value = "질문 수정", notes = "질문의 제목과 내용 수정이 가능합니다.")
    public ResponseEntity patchQuestion(@ApiParam("질문 Id") @PathVariable("question-id") @Positive Long questionId,
                                        @Valid @RequestBody QuestionDto.PatchDto patchDto) {
        Question question = mapper.patchDtoToQuestion(patchDto);
        Question updated = service.updateQuestion(questionId, question);

        QuestionDto.ResponseDto responseDto = mapper.questionToResponseDto(updated);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}")
    @ApiOperation(value = "질문 삭제", notes = "등록된 질문글을 question Id로 질문 삭제가 가능합니다.")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Positive @ApiParam(value = "질문 Id") Long questionId) {
        service.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    추천
    @PatchMapping("/{question-id}/up")
    @ApiOperation(value = "질문 추천(Up)")
    public ResponseEntity voteUp(@PathVariable("question-id") @Positive Long questionId) {
        Question voted = service.getPoint(questionId);

        QuestionDto.ResponseDto responseDto = mapper.questionToResponseDto(voted);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

//    비추천
    @PatchMapping("/{question-id}/down")
    @ApiOperation(value = "질문 추천(Down")
    public ResponseEntity voteDown(@PathVariable("question-id") @Positive Long questionId) {
        Question voted = service.losePoint(questionId);

        QuestionDto.ResponseDto responseDto = mapper.questionToResponseDto(voted);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
  
    //    검색
    @GetMapping("/search/questions")
    @ApiOperation(value = "검색 기능", notes = "keyword로 제목이나 작성글의 포함된 내용을 검색을 할 수 있습니다.")
    public ResponseEntity<Page<QuestionDto.ResponseDto>> searchQuestions(@PageableDefault Pageable pageable, @ApiParam(value = "제목 또는 내용")
                                                                         @RequestParam(required = false, defaultValue = "")
                                                                          String keyword) {
        Page<Question> searchResult = service.questionSearchList(keyword, keyword, pageable);
        Page<QuestionDto.ResponseDto> responsePage = searchResult.map(
                question -> mapper.questionToResponseDto(question));

        return ResponseEntity.ok(responsePage);
    }
}
