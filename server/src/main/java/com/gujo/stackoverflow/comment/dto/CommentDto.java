package com.gujo.stackoverflow.comment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    public static class PostDto {
        private String content;
    }

    @Getter
    @Setter
    public static class ResponseDto {
        private Long commentId;

        private Long answerId;

        private String content;

        private Long point;

        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;
    }

    @Getter
    public static class PatchDto {
        private String content;
    }

}
