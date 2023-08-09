package com.gujo.stackoverflow.question.dto;

import java.time.LocalDateTime;

public class QuestionDto {

    public class PostDto {
        private String title;

        private String content;

        private LocalDateTime createdAt = LocalDateTime.now();
    }
}
