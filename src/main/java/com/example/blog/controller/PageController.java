package com.example.blog.controller;

import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import com.example.blog.vo.Comment;
import com.example.blog.vo.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PageController {
    private final PostService postService;
    private final CommentService commentService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        List<Post> postList = postService.getPostByPage(1,3);
        model.addAttribute("posts", postList);
        return "index";
    }

    @RequestMapping("/post/{id}")
    public String getPostDetailPage(Model model, @PathVariable Integer id) {
        Post post = postService.getPostById(id);
        List<Comment> commentList = commentService.getCommentByPostIdAndPage(id, 1, 3);
        Integer commentCount = commentService.getCommentCount(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentList);
        model.addAttribute("commentCount", commentCount);
        return "post_detail";
    }

    @RequestMapping("/post/create")
    public String getPostCreatePage() {
        return "post_write";
    }

    @GetMapping("/post/edit/{id}")
    public String getPostCreatePage(Model model, @PathVariable Integer id) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "post_edit";
    }
}
