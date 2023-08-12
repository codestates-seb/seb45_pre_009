package com.gujo.stackoverflow.answer.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnswerDto {

    @Getter
    public static class PostDto {
        private String content;
    }

    @Getter @Setter
    public static class PatchDto {
        private Long answerId;  // 어떤 질문인지 받아와야 하니까 id 있어야
        private String content;
    }

    @Getter @Setter
    public static class ResponseDto {
        private Long answerId;
        private String content;
        private Long point;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

}