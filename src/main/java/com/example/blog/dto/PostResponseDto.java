package com.example.blog.dto;

import com.example.blog.vo.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    Integer idx;
    String title;
    String content;
    String username;

    public PostResponseDto(Post post) {
        this.idx = post.getIdx();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUsername();
    }
}
