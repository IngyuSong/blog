package com.example.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Comment {
    Integer id;
    String content;
    String username;
    Date createdAt;
    Integer postId;

}