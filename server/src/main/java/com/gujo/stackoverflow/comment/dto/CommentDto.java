package com.gujo.stackoverflow.comment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    @ApiModel(value = "댓글", description = "댓글 내용")
    public static class PostDto {
        @NotBlank(message = "댓글을 입력해 주세요")
        @ApiModelProperty(example="example text", required=true)
        private String content;
    }

    @Getter
    @Setter
    public static class ResponseDto {
        private Long commentId;

        private Long answerId;

        private Long memberId;

        private String content;

        private Long point;

        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;
    }

    @Getter
    @ApiModel(value = "댓글 수정", description = "댓글 내용")
    public static class PatchDto {
        @NotBlank(message = "댓글을 입력해 주세요")
        private String content;
    }

}
