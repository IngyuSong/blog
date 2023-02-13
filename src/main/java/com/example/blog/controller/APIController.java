package com.example.blog.controller;

import com.example.blog.dto.PostRequestDto;
import com.example.blog.dto.PostResponseDto;
import com.example.blog.service.PostService;
import com.example.blog.vo.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class APIController {
    private PostService postService;
    public APIController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/api/post")
    public List<PostResponseDto> getPostList(@RequestParam Integer page){
        List<Post> postList = postService.getPostsByPage(page);
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for(Post post: postList){
            postResponseDtoList.add(new PostResponseDto(post));
        }
        return postResponseDtoList;
    }

    @PostMapping("/post")
    public String createPost(@RequestBody PostRequestDto postRequestDto) {
        Post post = postRequestDto.getPost();
        postService.savePost(post);
        return "success";
    }

    @PutMapping("/post")
    public String updatePost(@RequestBody PostRequestDto postRequestDto) {
        Post post = postRequestDto.getPost();
        postService.updatePost(post);
        return "success";
    }

    @DeleteMapping("/post")
    public String deletePost(@RequestParam Integer id){
        postService.deletePost(id);
        return "success";
    }
}