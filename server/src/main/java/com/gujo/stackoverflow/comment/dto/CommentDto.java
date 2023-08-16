package com.gujo.stackoverflow.comment.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    public static class PostDto {
        @NotBlank(message = "댓글을 입력해 주세요")
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
        @NotBlank(message = "댓글을 입력해 주세요")
        private String content;
    }

}
