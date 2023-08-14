package com.gujo.stackoverflow.question.dto;

import com.gujo.stackoverflow.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class QuestionDto {

    @Getter
    public static class PostDto {
        @NotNull(message = "제목을 입력해 주세요")
        private String title;

        @NotNull(message = "내용을 입력해 주세요")
        private String content;
    }

    @Getter
    @Setter
    public static class ResponseDto {
        private Long questionId;

        private Member member;

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

        private Member member;

        private String title;

        private Long point;

        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;

        private Long views;
    }

    @Getter
    public static class PatchDto {
        private String title;

        private String content;
    }
}
