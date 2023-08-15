package com.gujo.stackoverflow.comment.mapper;

import com.gujo.stackoverflow.comment.dto.CommentDto;
import com.gujo.stackoverflow.comment.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment postDtoToComment(CommentDto.PostDto postDto);

    default CommentDto.ResponseDto commentToResponseDto(Comment comment) {
        CommentDto.ResponseDto responseDto = new CommentDto.ResponseDto();

        responseDto.setCommentId(comment.getCommentId());
        responseDto.setAnswerId(comment.getAnswer().getAnswerId());
        responseDto.setContent(comment.getContent());
        responseDto.setPoint(comment.getPoint());
        responseDto.setCreatedAt(comment.getCreatedAt());
        responseDto.setModifiedAt(comment.getModifiedAt());

        return responseDto;
    }


    List<CommentDto.ResponseDto> commentsToResponseDtos(List<Comment> comments);

    Comment patchDtoToComment(CommentDto.PatchDto patchDto);
}
