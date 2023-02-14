package com.example.blog.controller;

import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import com.example.blog.vo.Comment;
import com.example.blog.vo.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PageController {
    private PostService postService;
    private CommentService commentService;

    public PageController(PostService postService, CommentService commentService){
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String blogList(Model model){
        List<Post> postList = postService.getPostsByPage(0);
        model.addAttribute("posts",postList);
        return "index";
    }
    @RequestMapping("/post/{idx}")
    public String getBlogDetailPage(Model model, @PathVariable Integer idx){
        Post post = postService.getPostById(idx);
        List<Comment> commentList = commentService.getCommentsByPage(idx,0);
        Integer count = commentService.countComments(idx);
        model.addAttribute("comments",commentList);
        model.addAttribute("post",post);
        model.addAttribute("count",count);
        return "detailPost";

    }

    @RequestMapping("/post/create")
    public String getPostCreatePage() {
        return "newPost";
    }

    @RequestMapping("/post/edit/{idx}")
    public String getPostCreatePage(Model model, @PathVariable Integer idx){
        Post post = postService.getPostById(idx);
        model.addAttribute("post", post);
        return "editPost";
    }
}
