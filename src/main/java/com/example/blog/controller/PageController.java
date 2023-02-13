package com.example.blog.controller;

import com.example.blog.service.PostService;
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

    public PageController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/")
    public String blogList(Model model){
        List<Post> postList = postService.getPostsByPage(0);
        model.addAttribute("posts",postList);
        return "index";
    }
    @RequestMapping("/post/{id}")
    public String getBlogDetailPage(Model model, @PathVariable Integer id){
        Post post = postService.getPostById(id);
//        List<ReplyData> replyAll = replyService.getReplyDataByPage(id,0);
//        Integer count = replyService.countReplies(id);
//        model.addAttribute("reply",replyAll);
        model.addAttribute("post",post);
//        model.addAttribute("count",count);
        return "blog_detail";

    }

    @RequestMapping("/post/create")
    public String getPostCreatePage() {
        return "newPost";
    }

    @RequestMapping("/post/edit/{id}")
    public String getPostCreatePage(Model model, @PathVariable Integer id){
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "editPost";
    }
}
