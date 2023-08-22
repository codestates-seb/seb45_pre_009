package com.gujo.stackoverflow.question.dto;

import com.gujo.stackoverflow.member.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class QuestionDto {

    @Getter
    @ApiModel(value = "질문", description = "질문 제목, 질문 내용")
    public static class PostDto {
        @NotBlank(message = "제목을 입력해 주세요")
        @ApiModelProperty(example="question", required=true)
        private String title;

        @NotBlank(message = "내용을 입력해 주세요")
        @ApiModelProperty(example="example text", required=true)
        private String content;
    }

    @Getter
    @Setter
    public static class ResponseDto {
        private Long questionId;

        private Long memberId;

        private String title;

        private String content;

        private Long point;

        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;

        private Long views;
    }

    @Getter
    @Setter
    public static class getAllResponseDto {
        private Long questionId;

        private Long memberId;

        private String title;

        private Long point;

        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;

        private Long views;
    }

    @Getter
    @ApiModel(value = "질문 수정", description = "질문 제목, 질문 내용")
    public static class PatchDto {
        private String title;

        private String content;
    }
}
