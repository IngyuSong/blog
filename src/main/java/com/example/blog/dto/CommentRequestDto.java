package com.example.blog.dto;

import com.example.blog.vo.Comment;
import lombok.Setter;

import java.util.Date;

@Setter
public class CommentRequestDto {
    Integer id;
    String content;
    String username;
    Date createdAt;
    Integer postId;

    public Comment getComment() {
        return new Comment(this.id, this.content, this.username, this.createdAt, this.postId);
    }
}
