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

    default QuestionDto.ResponseDto questionToResponseDto(Question question) {
        QuestionDto.ResponseDto responseDto = new QuestionDto.ResponseDto();

        responseDto.setQuestionId(question.getQuestionId());
        responseDto.setMemberId(question.getMember().getMemberId());
        responseDto.setTitle(question.getTitle());
        responseDto.setContent(question.getContent());
        responseDto.setPoint(question.getPoint());
        responseDto.setViews(question.getViews());
        responseDto.setCreatedAt(question.getCreatedAt());
        responseDto.setModifiedAt(question.getModifiedAt());

        return responseDto;
    }

    List<QuestionDto.getAllResponseDto> questionToGetAllResponseDto(List<Question> questions);

     List<QuestionDto.ResponseDto> questionsToResponseDtos(Page<Question> questions);

    Question patchDtoToQuestion(QuestionDto.PatchDto patchDto);
}
