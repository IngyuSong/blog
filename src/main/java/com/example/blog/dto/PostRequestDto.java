package com.example.blog.dto;

import com.example.blog.vo.Post;
import lombok.Setter;

@Setter
public class PostRequestDto {
    Integer idx;
    String title;
    String content;
    String username;


    public Post getPost(){
        return new Post(this.idx, this.title, this.content, this.username);
    }

}