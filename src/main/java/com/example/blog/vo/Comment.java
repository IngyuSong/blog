package com.example.blog.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Comment {
    Integer idx;
    String content;
    String username;
    Date createdAt;
    Integer post_idx;

    public Comment(Integer idx, String content, String username, Integer post_idx) {
        this.idx = idx;
        this.content = content;
        this.username = username;
        this.post_idx = post_idx;
    }
}
