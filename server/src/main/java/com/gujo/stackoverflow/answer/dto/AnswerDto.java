package com.gujo.stackoverflow.answer.dto;

import com.gujo.stackoverflow.answer.entity.Answer;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AnswerDto {

    @Getter
    public static class PostDto {
        @NotBlank(message = "내용을 입력해 주세요")
        private String content;
    }

    @Getter @Setter
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
