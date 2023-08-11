package com.gujo.stackoverflow.answer.mapper;

import com.gujo.stackoverflow.answer.dto.AnswerDto;
import com.gujo.stackoverflow.answer.entity.Answer;
import com.gujo.stackoverflow.answer.repository.AnswerRepository;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostDtoToAnswer(AnswerDto.PostDto postDto);
    Answer answerPatchDtoToAnswer(AnswerDto.PatchDto patchDto);
    AnswerDto.ResponseDto answerToAnswerResponseDto(Answer answer);
    List<AnswerDto.ResponseDto> answersToAnswerResponseDtos(List<Answer> answers);
}
