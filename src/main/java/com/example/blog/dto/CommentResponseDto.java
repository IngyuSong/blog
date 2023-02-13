package com.example.blog.dto;

import com.example.blog.vo.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    Integer idx;
    String content;
    String username;
    Integer post_idx;

    public CommentResponseDto(Comment comment){
        this.idx = comment.getIdx();
        this.content = comment.getContent();
        this.username = comment.getUsername();
        this.post_idx = comment.getPost_idx();
    }
}
