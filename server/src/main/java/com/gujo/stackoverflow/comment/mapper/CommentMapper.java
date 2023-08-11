package com.gujo.stackoverflow.comment.mapper;

import com.gujo.stackoverflow.comment.dto.CommentDto;
import com.gujo.stackoverflow.comment.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment postDtoToComment(CommentDto.PostDto postDto);

    CommentDto.ResponseDto commentToResponseDto(Comment created);

    List<CommentDto.ResponseDto> commentsToResponseDtos(List<Comment> comments);

    Comment patchDtoToComment(CommentDto.PatchDto patchDto);
}
