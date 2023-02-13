package com.example.blog.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Post {
    Integer idx;
    String title;
    String content;
    String username;
    Date createdAt;

}
