package com.example.blog.dto;

import com.example.blog.vo.Comment;
import lombok.Setter;

@Setter
public class CommentRequestDto {
    Integer id;
    Integer postId;
    String content;
    String username;

    public Comment getComment() {
        return new Comment(this.id, this.postId, this.content, this.username);
    }
}
