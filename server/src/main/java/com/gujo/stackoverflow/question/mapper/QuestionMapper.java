package com.gujo.stackoverflow.question.mapper;

import com.gujo.stackoverflow.question.dto.QuestionDto;
import com.gujo.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question postDtoToQuestion(QuestionDto.PostDto postDto);

    QuestionDto.ResponseDto questionToResponseDto(Question question);

    QuestionDto.getAllResponseDto questionToGetAllResponseDto(Question question);

    Question patchDtoToQuestion(QuestionDto.PatchDto patchDto);
}
