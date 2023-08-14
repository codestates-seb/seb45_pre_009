package com.gujo.stackoverflow.question.mapper;

import com.gujo.stackoverflow.question.dto.QuestionDto;
import com.gujo.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question postDtoToQuestion(QuestionDto.PostDto postDto);

    QuestionDto.ResponseDto questionToResponseDto(Question question);

    List<QuestionDto.getAllResponseDto> questionToGetAllResponseDto(List<Question> questions);

     List<QuestionDto.ResponseDto> questionsToResponseDtos(Page<Question> questions);

    Question patchDtoToQuestion(QuestionDto.PatchDto patchDto);
}
