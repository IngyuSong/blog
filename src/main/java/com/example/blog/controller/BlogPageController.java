package com.example.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class BlogPageController {

    @GetMapping("/")
    public String blogList(){
        return "index";
    }
}
