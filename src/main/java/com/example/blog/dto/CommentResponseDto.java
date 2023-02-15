package com.example.blog.dto;

import com.example.blog.vo.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    Integer id;
    String content;
    String username;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUsername();
    }
}
