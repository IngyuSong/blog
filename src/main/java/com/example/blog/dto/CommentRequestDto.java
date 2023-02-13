package com.example.blog.dto;

import com.example.blog.vo.Comment;
import lombok.Setter;

@Setter
public class CommentRequestDto {
    Integer idx;
    String content;
    String username;
    Integer post_idx;

    public Comment getComment() {
        return new Comment(this.idx, this.content, this.username, this.post_idx);
    }
}
