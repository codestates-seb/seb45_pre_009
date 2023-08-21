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

//    questionId 값 자동 매핑 안되서 수동 매핑
    default AnswerDto.ResponseDto answerToAnswerResponseDto(Answer answer) {
        AnswerDto.ResponseDto responseDto = new AnswerDto.ResponseDto();

        responseDto.setAnswerId(answer.getAnswerId());
        responseDto.setQuestionId(answer.getQuestion().getQuestionId());
        responseDto.setMemberId(answer.getMember().getMemberId());
        responseDto.setPoint(answer.getPoint());
        responseDto.setContent(answer.getContent());
        responseDto.setCreatedAt(answer.getCreatedAt());
        responseDto.setModifiedAt(answer.getModifiedAt());

        return responseDto;
    }
    List<AnswerDto.ResponseDto> answersToAnswerResponseDtos(List<Answer> answers);
}
