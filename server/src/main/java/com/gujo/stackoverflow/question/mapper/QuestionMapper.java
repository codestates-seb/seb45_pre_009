package com.gujo.stackoverflow.question.mapper;

import com.gujo.stackoverflow.question.dto.QuestionDto;
import com.gujo.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionDto.PostDto postDto);

    QuestionDto.ResponseDto questionToQuestionResponseDto(Question question);

    QuestionDto.getQuestionsResponseDto questionToGetQuestionsResponseDto(Question question);

    Question questionPatchDtoToQuestion(QuestionDto.PatchDto patchDto);
}
