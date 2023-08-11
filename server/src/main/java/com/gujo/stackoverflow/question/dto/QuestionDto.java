package com.gujo.stackoverflow.question.dto;

import com.gujo.stackoverflow.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class QuestionDto {

    @Getter
    public static class PostDto {
        private String title;

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

        private Long view;
    }

    @Getter
    @Setter
    public static class getAllResponseDto {
        private Long questionId;

        private Member member;

        private String title;

        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;

        private Long view;
    }

    @Getter
    public static class PatchDto {
        private String title;

        private String content;
    }
}