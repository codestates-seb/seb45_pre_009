package com.gujo.stackoverflow.answer.dto;

import com.gujo.stackoverflow.answer.entity.Answer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AnswerDto {

    @Getter
    @ApiModel(value = "답변", description = "답변 내용")
    public static class PostDto {
        @NotBlank(message = "내용을 입력해 주세요")
        @ApiModelProperty(example="example text", required=true)
        private String content;
    }

    @Getter @Setter
    @ApiModel(value = "답변 수정", description = "답변 내용")
    public static class PatchDto {
        private Long answerId;  // 어떤 질문인지 받아와야 하니까 id 있어야

        @NotNull(message = "내용을 입력해 주세요")
        private String content;
        private Answer.AnswerStatus answerStatus;
    }

    @Getter @Setter
    public static class ResponseDto {
        private Long answerId;
        private Long questionId;
        private String content;
        private Long point;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private Answer.AnswerStatus answerStatus;

    }

}
